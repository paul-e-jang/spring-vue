package bashpound.marketplace.domain.application.commands;

import org.springframework.util.Assert;

import java.util.Objects;

public class RegisterCommand extends AnonymousCommand {

  private String username;
  private String emailAddress;
  private String password;
  private String name;
  private String nickName;

  public RegisterCommand(String username, String emailAddress, String password, String name, String nickName) {
    Assert.hasText(username, "Parameter `username` must not be empty");
    Assert.hasText(emailAddress, "Parameter `emailAddress` must not be empty");
    Assert.hasText(password, "Parameter `password` must not be empty");
    Assert.hasText(name, "Parameter `firatName` must not be empty");
    Assert.hasText(nickName, "Parameter `lastName` must not be empty");

    this.username = username;
    this.emailAddress = emailAddress;
    this.password = password;
    this.name = name;
    this.nickName = nickName;
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmailAddress() {
    return this.emailAddress;
  }

  public String getPassword() {
    return this.password;
  }
  

  public String getname() {
	return this.name;
}

public String getNickName() {
	return this.nickName;
}

@Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RegisterCommand)) return false;
    RegisterCommand that = (RegisterCommand) o;
    return Objects.equals(username, that.username) &&
      Objects.equals(emailAddress, that.emailAddress) &&
      Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, emailAddress, password);
  }

  @Override
  public String toString() {
    return "RegisterCommand{" +
      "username='" + username + '\'' +
      ", emailAddress='" + emailAddress + '\'' +
      ", password='" + password + '\'' +
      ", name='" + name + '\'' +
      ", nickName='" + nickName + '\'' +
      '}';
  }
}
