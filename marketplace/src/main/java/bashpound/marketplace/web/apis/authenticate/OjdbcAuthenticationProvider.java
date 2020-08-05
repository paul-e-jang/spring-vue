package bashpound.marketplace.web.apis.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class OjdbcAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		if(authentication == null) {
			throw new InternalAuthenticationServiceException("Authentication is null");
		}
	String username = authentication.getName();
		if(authentication.getCredentials()==null) {
		throw new AuthenticationCredentialsNotFoundException("Credential is null");
	}
	String password = authentication.getCredentials().toString();
	UserDetails loadedUser = customUserDetailsService.loadUserByUsername(username);
	
		if(loadedUser == null) {
		throw new InternalAuthenticationServiceException("UserDetailsService is null, which is an interface contract violation");
	}
	
	if(!loadedUser.isAccountNonLocked()) {
		throw new LockedException("User account is locked.");
	}
	if(!loadedUser.isEnabled()) {
		throw new DisabledException("User is disabled.");
	}
	if(!passwordEncoder.matches(password, loadedUser.getPassword())) {
		throw new BadCredentialsException("Password does not match.");
	}
	if(!loadedUser.isCredentialsNonExpired()) {
		throw new CredentialsExpiredException("Your password has been expired.");
	}
	UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities());
	result.setDetails(authentication.getDetails());
	return result;
	}
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
