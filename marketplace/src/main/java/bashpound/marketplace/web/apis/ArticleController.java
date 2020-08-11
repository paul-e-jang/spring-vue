package bashpound.marketplace.web.apis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bashpound.marketplace.domain.application.ArticleService;
import bashpound.marketplace.domain.model.article.ArticleLoadException;
import bashpound.marketplace.domain.model.article.Articles;


@Controller
public class ArticleController extends AbstractBaseController{
	
	ArticleService articleService;
	
	public ArticleController(ArticleService articleService) {
		super();
		this.articleService = articleService;
	}
	
    @RequestMapping(value="/api/{boardname}/articles")
    @ResponseBody
    public Articles getArticles(Model model, @PathVariable("boardname") String boardname)
    		throws ArticleLoadException {

    	Articles articles = new Articles();
    	articles.addArticles(articleService.findByBoardName(boardname));
    	return articles;
    }
}
