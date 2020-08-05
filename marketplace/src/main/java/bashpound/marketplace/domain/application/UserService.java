package bashpound.marketplace.domain.application;

import bashpound.marketplace.domain.model.user.Users;
import bashpound.marketplace.domain.model.user.UserId;

import bashpound.marketplace.domain.application.commands.RegistrationCommand;
import bashpound.marketplace.domain.model.user.RegistrationException;

public interface UserService {
/**
	* Find user by id
	*
	* @param userId the id of the user
	* @return a user instance or null if not found
	*/
	Users findById(UserId userId);	
	

  /**
   * Register a new user with username, email address, and password.
   *
   * @param command instance of <code>RegistrationCommand</code>
   * @throws RegistrationException when registration failed. Possible reasons are:
   *                               1) Username already exists
   *                               2) Email address already exists.
   */
  void register(RegistrationCommand command) throws RegistrationException;
  

  
}
