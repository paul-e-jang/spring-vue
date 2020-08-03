package bashpound.marketplaceTemp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import bashpound.marketplaceTemp.web.apis.authenticate.AuthenticationFilter;
import bashpound.marketplaceTemp.web.apis.authenticate.SimpleAuthenticationFailureHandler;
import bashpound.marketplaceTemp.web.apis.authenticate.SimpleAuthenticationSuccessHandler;
import bashpound.marketplaceTemp.web.apis.authenticate.SimpleLogoutSuccessHandler;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String[] PUBLIC = new String[]{
    "/error", "/loginpage", "/logout", "/register", "/api/registrations"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers(PUBLIC).permitAll()
        .anyRequest().authenticated()
      .and()
        .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .formLogin()
        .loginPage("/loginpage")
      .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(logoutSuccessHandler())
      .and()
        .csrf().disable();
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/img/**", "/images/**", "/favicon.ico");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationFilter authenticationFilter() throws Exception {
    AuthenticationFilter authenticationFilter = new AuthenticationFilter();
    authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
    authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
    authenticationFilter.setAuthenticationManager(authenticationManagerBean());
    return authenticationFilter;
  }

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new SimpleAuthenticationSuccessHandler();
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new SimpleAuthenticationFailureHandler();
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new SimpleLogoutSuccessHandler();
  }
}
