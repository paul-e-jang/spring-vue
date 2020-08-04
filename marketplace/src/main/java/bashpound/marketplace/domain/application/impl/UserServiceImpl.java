package bashpound.marketplace.domain.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import bashpound.marketplace.domain.model.user.UserRepository;
import bashpound.marketplace.domain.application.UserService;
import bashpound.marketplace.domain.application.commands.RegistrationCommand;
import bashpound.marketplace.domain.common.event.DomainEventPublisher;
import bashpound.marketplace.domain.common.mail.MailManager;
import bashpound.marketplace.domain.common.mail.MessageVariable;
import bashpound.marketplace.domain.model.user.RegistrationException;
import bashpound.marketplace.domain.model.user.RegistrationManagement;
import bashpound.marketplace.domain.model.user.Users;
import bashpound.marketplace.domain.model.user.UserId;
import bashpound.marketplace.domain.model.user.events.UserRegisteredEvent;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private RegistrationManagement registrationManagement;
  private DomainEventPublisher domainEventPublisher;
  private MailManager mailManager;
  private UserRepository userRepository;

  public UserServiceImpl(RegistrationManagement registrationManagement,
                         DomainEventPublisher domainEventPublisher,
                         MailManager mailManager, UserRepository userRepository) {
    this.registrationManagement = registrationManagement;
    this.domainEventPublisher = domainEventPublisher;
    this.mailManager = mailManager;
    this.userRepository = userRepository;
  }
  
  @Override
  public Users findById(UserId userId) {
    return userRepository.findById(userId);
  }
  
  @Override
  public boolean authenticate(String username, String password) {
	boolean judge = false;
    Users user = userRepository.authenticate(username, password);
    if(user.getUsername().equals(username)) {
    	judge = true;
    }
    return judge;
  }

  @Override
  public void register(RegistrationCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
    Users newUser = registrationManagement.register(
      command.getUsername(),
      command.getEmailAddress(),
      command.getPassword());

    sendWelcomeMessage(newUser);
    domainEventPublisher.publish(new UserRegisteredEvent(newUser));
  }

  private void sendWelcomeMessage(Users users) {
    mailManager.send(
      users.getEmailAddress(),
      "Welcome to Bashpound Marketplace",
      "welcome.ftl",
      MessageVariable.from("user", users)
    );
  }
}
