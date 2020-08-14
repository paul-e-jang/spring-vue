package bashpound.marketplace.web.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bashpound.marketplace.domain.application.commands.RegisterCommand;

public class RegistrationPayload {

  @Size(min = 2, max = 20, message = "아이디는 최소 2글글자, 최대 20글자 입니다.")
  @NotNull
  private String username;

  @Email(message = "이메일 주소가 유효하지 않습니다.")
  @Size(max = 100, message = "이메일 주소는 100글자를 초과할 수 없습니다.")
  @NotNull
  private String emailAddress;

  @Size(min = 10, max = 30, message = "패스워드는 30자를 초과할 수 없습니다.")
  @NotNull
  private String password;
  
  @Size(min = 10, max = 30, message = "이름은 최대 10글자 입니다.")
  @NotNull
  private String firstName;
  
  @Size(min = 10, max = 30, message = "닉네임은 최대 10글자 입니다.")
  @NotNull
  private String lastName;

  public RegisterCommand toCommand() {
    return new RegisterCommand(this.username, this.emailAddress, this.password, this.firstName, this.lastName);
  }

public String getUsername() {
	return username;
}

public String getEmailAddress() {
	return emailAddress;
}

public String getPassword() {
	return password;
}

public String getFirstName() {
	return firstName;
}

public String getLastName() {
	return lastName;
}

public void setUsername(String username) {
	this.username = username;
}

public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}

public void setPassword(String password) {
	this.password = password;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

}
