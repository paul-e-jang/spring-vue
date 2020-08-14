package bashpound.marketplace.domain.application;

import org.springframework.security.core.userdetails.UserDetailsService;

import bashpound.marketplace.domain.application.commands.RegisterCommand;
import bashpound.marketplace.domain.model.user.RegistrationException;
import bashpound.marketplace.domain.model.user.UserId;
import bashpound.marketplace.domain.model.user.Users;

public interface UserService extends UserDetailsService{
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
  void register(RegisterCommand command) throws RegistrationException;
  
  /**
   * 
   * @param param
   * @param value
   * @return boolean check if param of value already exists in DB
   */
  boolean checkAlready(String param, String value);
}
