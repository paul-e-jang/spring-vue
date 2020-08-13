package bashpound.marketplace.web.apis;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bashpound.marketplace.domain.application.ArticleService;
import bashpound.marketplace.domain.model.article.ArticleLoadException;
import bashpound.marketplace.domain.model.article.ArticleRegistrationException;
import bashpound.marketplace.domain.model.article.Articles;
import bashpound.marketplace.domain.model.article.ExecuteUpdateException;
import bashpound.marketplace.domain.model.article.Replies;
import bashpound.marketplace.web.payload.WrtieArticlePayload;
import bashpound.marketplace.web.payload.WrtieReplyPayload;
import bashpound.marketplace.web.results.ApiResult;
import bashpound.marketplace.web.results.Result;


@Controller
public class ArticleApiController extends AbstractBaseController{
	
	ArticleService service;
	
	public ArticleApiController(ArticleService articleService) {
		super();
		this.service = articleService;
	}
	
    @RequestMapping(value="/api/{boardname}/articles")
    @ResponseBody
    public Articles getArticles(Model model, @PathVariable("boardname") String boardname)
    		throws ArticleLoadException {

    	Articles articles = new Articles();
    	articles.addArticles(service.findByBoardName(boardname));
    	return articles;
    }

    @RequestMapping(value="/api/{articleCode}/replies")
    @ResponseBody
    public Replies getReplies(Model model, @PathVariable("articleCode") Long articleCode)
    		throws ArticleLoadException {

    	Replies replies = new Replies();
    	replies.addReplies(service.findRepliesByArticleCode(articleCode));
    	return replies;
    }
    
	@PostMapping("/api/writeArticle")
	public ResponseEntity<ApiResult> articleRegister(@Valid @RequestBody WrtieArticlePayload payload) {
	  try {
	    service.articleRegister(payload.toCommand());
	    return Result.created();
	  } catch (ArticleRegistrationException e) {
	    String errorMessage = "게시글 등록에 실패하였습니다.";
	    return Result.failure(errorMessage);
	  }
	}
	@PostMapping("/api/writeReply")
	public ResponseEntity<ApiResult> replyRegister(@Valid @RequestBody WrtieReplyPayload payload) {
      try {
      service.replyRegister(payload.toCommand());
	    return Result.created();
      } catch (ArticleRegistrationException e) {
	    String errorMessage = "댓글 등록에 실패하였습니다.";
      return Result.failure(errorMessage);
      }
	}
	
	@PostMapping("/api/view/{id}/{how}")
	public void viewUpdate(@PathVariable("id") Long id, @PathVariable("how") String how)
			throws ExecuteUpdateException, ArticleRegistrationException {

      service.updateViews(id, parseHow(how));
	}
	
	@PostMapping("/api/reply/{id}/{how}")
	public void replyUpdate(@PathVariable("id") Long id, @PathVariable("how") String how)
			throws ExecuteUpdateException, ArticleRegistrationException {
      service.updateReplies(id, parseHow(how));
	}
	
	public static int parseHow(String how) {
		int i;
		if(how.equals("pp")) {
			i = 1;
		}else if(how.equals("mm")) {
			i = -1;
		}else { i = 0; }
		return i;
	}
}


