package bashpound.marketplace.web.apis;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import bashpound.marketplace.domain.model.article.Article;
import bashpound.marketplace.domain.model.article.ArticleRepository;


@Controller
public class ArticleController {
	
	ArticleRepository articleRepository;
	
	public ArticleController(ArticleRepository articleRepository) {
		    this.articleRepository = articleRepository;
	}
	
    @GetMapping(value="/api/{boardname}/articles", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getArticlesJson(Model model, @PathVariable("boardname") String boardname) {
    	
    	List<Article> articles = articleRepository.findByBoardName(boardname);
    	List<JSONObject> entities = new ArrayList<JSONObject>();
    	model.addAttribute("articles", "something");
        return "jsonmembertemplate";
    }
}
