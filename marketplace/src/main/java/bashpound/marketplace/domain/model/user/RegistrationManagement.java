package bashpound.marketplace.domain.model.user;

import org.springframework.stereotype.Component;
import bashpound.marketplace.domain.common.security.PasswordEncryptor;

@Component
public class RegistrationManagement {

	  private UserRepository repository;
	  private PasswordEncryptor passwordEncryptor;

	  public RegistrationManagement(UserRepository repository, PasswordEncryptor passwordEncryptor) {
	    this.repository = repository;
	    this.passwordEncryptor = passwordEncryptor;
	  }

	  public Users register(String username, String emailAddress, String password, String name, String nickName)
	    throws RegistrationException {
	    Users existingUser = repository.findByUsername(username);
	    if (existingUser != null) {
	      throw new UsernameExistsException();
	    }

	    existingUser = repository.findByEmailAddress(emailAddress.toLowerCase());
	    if (existingUser != null) {
	      throw new EmailAddressExistsException();
	    }

	    String encryptedPassword = passwordEncryptor.encrypt(password);
	    Users newUser = Users.create(username, emailAddress.toLowerCase(), encryptedPassword, name, nickName);
	    repository.save(newUser);
	    return newUser;
	  }
}
