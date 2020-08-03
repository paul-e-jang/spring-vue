package bashpound.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(
  basePackages = {"bashpound.marketplace.infrastructure.file.local"}
)
@SpringBootApplication
public class MarketplaceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarketplaceApplication.class, args);
  }
}
