package bashpound.marketplaceTemp.domain.application;

import bashpound.marketplaceTemp.domain.application.commands.RegistrationCommand;
import bashpound.marketplaceTemp.domain.model.user.RegistrationException;

public interface UserService {

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
