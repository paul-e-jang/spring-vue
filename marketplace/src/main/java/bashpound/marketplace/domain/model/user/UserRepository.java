package bashpound.marketplace.domain.model.user;

/**
 * User repository interface
 */
public interface UserRepository {

  /**
   * Find user by a username
   *
   * @param username the provided username used for finding user
   * @return an instance of <code>User</code> if found, null otherwise
   */
  Users findByUsername(String username);

  /**
   * Find user by an email address
   *
   * @param emailAddress the provided email address used for finding user
   * @return an instance of <code>User</code> if found, null otherwise
   */
  Users findByEmailAddress(String emailAddress);
  
  /**
   * Find user by id
   *
   * @param userId the id of the user
   * @return a user instance or null if not found
   */
  Users findById(UserId userId);  

  /**
   * Save a new user or an existing user
   *
   * @param users the user instance to be saved
   * @return the newly save user
   */
  void save(Users users);

  /**
  @param username
  @return null or user
  */
  Users authenticate(String username, String password);
}
