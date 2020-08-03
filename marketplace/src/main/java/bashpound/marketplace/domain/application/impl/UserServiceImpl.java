package bashpound.marketplace.domain.application.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import bashpound.marketplace.domain.application.UserService;
import bashpound.marketplace.domain.application.commands.RegisterCommand;
import bashpound.marketplace.domain.common.event.DomainEventPublisher;
import bashpound.marketplace.domain.common.mail.MailManager;
import bashpound.marketplace.domain.common.mail.MessageVariable;
import bashpound.marketplace.domain.model.user.*;
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
                         MailManager mailManager,
                         UserRepository userRepository) {
    this.registrationManagement = registrationManagement;
    this.domainEventPublisher = domainEventPublisher;
    this.mailManager = mailManager;
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (StringUtils.isEmpty(username)) {
      throw new UsernameNotFoundException("No user found");
    }
    Users user;
    if (username.contains("@")) {
      user = userRepository.findByEmailAddress(username);
    } else {
      user = userRepository.findByUsername(username);
    }
    if (user == null) {
      throw new UsernameNotFoundException("No user found by `" + username + "`");
    }
    return new SimpleUser(user);
  }

  @Override
  public Users findById(UserId userId) {
    return userRepository.findById(userId);
  }

  @Override
  public void register(RegisterCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
    Users newUser = registrationManagement.register(
      command.getUsername(),
      command.getEmailAddress(),
      command.getFirstName(),
      command.getLastName(),
      command.getPassword());

    sendWelcomeMessage(newUser);
    domainEventPublisher.publish(new UserRegisteredEvent(newUser, command));
  }

  private void sendWelcomeMessage(Users user) {
    mailManager.send(
      user.getEmailAddress(),
      "Welcome to TaskAgile",
      "welcome.ftl",
      MessageVariable.from("user", user)
    );
  }
}
