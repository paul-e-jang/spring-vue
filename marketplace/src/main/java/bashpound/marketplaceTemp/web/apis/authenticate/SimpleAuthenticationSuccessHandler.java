package bashpound.marketplaceTemp.web.apis.authenticate;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import bashpound.marketplaceTemp.utils.JsonUtils;
import bashpound.marketplaceTemp.web.results.ApiResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException {
    response.setStatus(HttpStatus.OK.value());
    JsonUtils.write(response.getWriter(), ApiResult.message("authenticated"));
  }
}
