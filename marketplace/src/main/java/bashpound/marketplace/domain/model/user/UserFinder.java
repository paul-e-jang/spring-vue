package bashpound.marketplace.domain.model.user;

import org.springframework.stereotype.Component;

@Component
public class UserFinder {

  private UserRepository userRepository;

  public UserFinder(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Users find(String usernameOrEmailAddress) throws UserNotFoundException {
    Users user;
    if (usernameOrEmailAddress.contains("@")) {
      user = userRepository.findByEmailAddress(usernameOrEmailAddress);
    } else {
      user = userRepository.findByUsername(usernameOrEmailAddress);
    }
    if (user == null) {
      throw new UserNotFoundException();
    }
    return user;
  }
}
