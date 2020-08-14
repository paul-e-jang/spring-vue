package bashpound.marketplace.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bashpound.marketplace.domain.application.UserService;
import bashpound.marketplace.domain.model.user.EmailAddressExistsException;
import bashpound.marketplace.domain.model.user.RegistrationException;
import bashpound.marketplace.domain.model.user.UsernameExistsException;
import bashpound.marketplace.web.payload.RegistrationPayload;
import bashpound.marketplace.web.results.ApiResult;
import bashpound.marketplace.web.results.Result;

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
      String errorMessage = "유저 등록에 실패했습니다. 이유:";
      if (e instanceof UsernameExistsException) {
        errorMessage = "이미 등록된 아이디입니다.";
      } else if (e instanceof EmailAddressExistsException) {
        errorMessage = "이미 등록된 이메일입니다.";
      }
      return Result.failure(errorMessage);
    }
  }
}
