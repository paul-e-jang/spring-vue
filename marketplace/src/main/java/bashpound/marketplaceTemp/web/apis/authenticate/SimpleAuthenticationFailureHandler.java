package bashpound.marketplaceTemp.web.apis.authenticate;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import bashpound.marketplaceTemp.utils.JsonUtils;
import bashpound.marketplaceTemp.web.results.ApiResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws IOException {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    ApiResult failure;
    if (exception instanceof BadCredentialsException) {
      failure = ApiResult.message("Invalid credentials");
    } else if (exception instanceof InsufficientAuthenticationException) {
      failure = ApiResult.message("Invalid authentication request");
    } else {
      failure = ApiResult.message("Authentication failure");
    }
    JsonUtils.write(response.getWriter(), failure);
  }
}
