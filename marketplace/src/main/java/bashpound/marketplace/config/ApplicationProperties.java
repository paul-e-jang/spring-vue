package bashpound.marketplace.config;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


@Configuration
@ConfigurationProperties(prefix="app")
@Validated
public class ApplicationProperties {

  /**
   * Default `from` value of emails sent out by the system
   */
  @Email
  @NotBlank
  private String mailFrom;
  
  @NotNull
  private String uploadDir;

  public void setMailFrom(String mailFrom) {
    this.mailFrom = mailFrom;
  }

  public String getMailFrom() {
    return mailFrom;
  }

public String getUploadDir() {
	return uploadDir;
}

public void setUploadDir(String uploadDir) {
	this.uploadDir = uploadDir;
}
  

}
