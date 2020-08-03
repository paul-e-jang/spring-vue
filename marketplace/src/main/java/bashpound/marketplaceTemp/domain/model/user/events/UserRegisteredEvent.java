package bashpound.marketplaceTemp.domain.model.user.events;

import org.springframework.util.Assert;

import bashpound.marketplaceTemp.domain.common.event.DomainEvent;
import bashpound.marketplaceTemp.domain.model.user.Users;

public class UserRegisteredEvent extends DomainEvent {

  private static final long serialVersionUID = 2580061707540917880L;

  private Users users;

  public UserRegisteredEvent(Users users) {
    super(users);
    Assert.notNull(users, "Parameter `user` must not be null");
    this.users = users;
  }

  public Users getUser() {
    return this.users;
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserRegisteredEvent that = (UserRegisteredEvent) o;
    return that.users.equals(this.users);
  }

  public int hashCode() {
    return this.users.hashCode();
  }

  public String toString() {
    return "UserRegisteredEvent{" +
      "user='" + users + '\'' +
      "timestamp='" + getTimestamp() + '\'' +
      '}';
  }
}
