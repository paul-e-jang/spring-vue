package bashpound.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@SpringBootApplication
public class MarketplaceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarketplaceApplication.class, args);
  }
  
  @Bean
  public View toJson() {
      MappingJackson2JsonView view = new MappingJackson2JsonView();
      view.setPrettyPrint(true);
      return view;
  }
}
