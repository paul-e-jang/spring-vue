package bashpound.marketplace.web.apis;

import bashpound.marketplace.domain.application.ArticleService;
import bashpound.marketplace.domain.model.article.ArticleRegistrationException;
import bashpound.marketplace.web.payload.WrtieArticlePayload;
import bashpound.marketplace.web.results.ApiResult;
import bashpound.marketplace.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Controller
public class WrtieArticleApiController extends AbstractBaseController {

	  private ArticleService service;

	  public WrtieArticleApiController(ArticleService service) {
	    this.service = service;
	  }

	  @PostMapping("/api/write")
	  public ResponseEntity<ApiResult> register(@Valid @RequestBody WrtieArticlePayload payload) {
	    try {
	      service.register(payload.toCommand());
	      return Result.created();
	    } catch (ArticleRegistrationException e) {
	      String errorMessage = "Registration failed";
	      return Result.failure(errorMessage);
	    }
	  }
}
