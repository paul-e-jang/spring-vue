package bashpound.marketplace.domain.model.user.events;

import bashpound.marketplace.domain.common.event.DomainEvent;
import bashpound.marketplace.domain.common.event.TriggeredFrom;
import bashpound.marketplace.domain.model.user.Users;

public class UserRegisteredEvent extends DomainEvent {

  private static final long serialVersionUID = 2580061707540917880L;

  public UserRegisteredEvent(Users user, TriggeredFrom triggeredFrom) {
    super(user.getId(), triggeredFrom);
  }

  @Override
  public String toString() {
    return "UserRegisteredEvent{userId=" + getUserId() + '}';
  }
}
