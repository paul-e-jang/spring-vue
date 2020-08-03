package bashpound.marketplaceTemp.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bashpound.marketplaceTemp.domain.application.UserService;
import bashpound.marketplaceTemp.domain.model.user.EmailAddressExistsException;
import bashpound.marketplaceTemp.domain.model.user.RegistrationException;
import bashpound.marketplaceTemp.domain.model.user.UsernameExistsException;
import bashpound.marketplaceTemp.web.payload.RegistrationPayload;
import bashpound.marketplaceTemp.web.results.ApiResult;
import bashpound.marketplaceTemp.web.results.Result;

import javax.validation.Valid;

@Controller
public class RegistrationApiController {

  private UserService service;

  public RegistrationApiController(UserService service) {
    this.service = service;
  }

  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload) {
    try {
      service.register(payload.toCommand());
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
