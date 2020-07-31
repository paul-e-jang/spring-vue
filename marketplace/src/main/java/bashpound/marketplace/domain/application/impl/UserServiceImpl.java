package bashpound.marketplace.domain.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import bashpound.marketplace.domain.application.UserService;
import bashpound.marketplace.domain.application.commands.RegistrationCommand;
import bashpound.marketplace.domain.common.event.DomainEventPublisher;
import bashpound.marketplace.domain.common.mail.MailManager;
import bashpound.marketplace.domain.common.mail.MessageVariable;
import bashpound.marketplace.domain.model.user.RegistrationException;
import bashpound.marketplace.domain.model.user.RegistrationManagement;
import bashpound.marketplace.domain.model.user.Users;
import bashpound.marketplace.domain.model.user.events.UserRegisteredEvent;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private RegistrationManagement registrationManagement;
  private DomainEventPublisher domainEventPublisher;
  private MailManager mailManager;

  public UserServiceImpl(RegistrationManagement registrationManagement,
                         DomainEventPublisher domainEventPublisher,
                         MailManager mailManager) {
    this.registrationManagement = registrationManagement;
    this.domainEventPublisher = domainEventPublisher;
    this.mailManager = mailManager;
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
