package bashpound.marketplace.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bashpound.marketplace.domain.application.UserService;
import bashpound.marketplace.domain.application.commands.RegisterCommand;
import bashpound.marketplace.domain.model.user.EmailAddressExistsException;
import bashpound.marketplace.domain.model.user.RegistrationException;
import bashpound.marketplace.domain.model.user.UsernameExistsException;
import bashpound.marketplace.web.payload.RegistrationPayload;
import bashpound.marketplace.web.results.ApiResult;
import bashpound.marketplace.web.results.Result;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationApiController extends AbstractBaseController {

  private UserService service;

  public RegistrationApiController(UserService service) {
    this.service = service;
  }

  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload,
                                            HttpServletRequest request) {
    try {
      RegisterCommand command = payload.toCommand();
      addTriggeredBy(command, request);

      service.register(command);
      return Result.created();
    } catch (RegistrationException e) {
      String errorMessage = "Registration failed";
      if (e instanceof UsernameExistsException) {
        errorMessage = "Username already exists";
      } else if (e instanceof EmailAddressExistsException) {
        errorMessage = "Email address already exists";
      }
      return Result.failure(errorMessage);
    }
  }
}
