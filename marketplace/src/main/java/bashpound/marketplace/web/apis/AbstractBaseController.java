package bashpound.marketplace.web.apis;

import bashpound.marketplace.domain.application.commands.AnonymousCommand;
import bashpound.marketplace.domain.application.commands.UserCommand;
import bashpound.marketplace.domain.model.user.SimpleUser;
import bashpound.marketplace.utils.RequestUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController {

  void addTriggeredBy(UserCommand command, HttpServletRequest request) {
    Assert.notNull(request.getUserPrincipal(), "User principal must be present in the request");
    UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
    SimpleUser currentUser = (SimpleUser) userPrincipal.getPrincipal();
    command.triggeredBy(currentUser.getUserId(), RequestUtils.getIpAddress(request));
  }

  void addTriggeredBy(AnonymousCommand command, HttpServletRequest request) {
    command.triggeredBy(RequestUtils.getIpAddress(request));
  }
}
