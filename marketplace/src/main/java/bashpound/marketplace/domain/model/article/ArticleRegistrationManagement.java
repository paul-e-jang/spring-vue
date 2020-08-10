package bashpound.marketplace.domain.model.article;

import org.springframework.stereotype.Component;

@Component
public class ArticleRegistrationManagement {

	private ArticleRepository repository;

	public ArticleRegistrationManagement(ArticleRepository repository) {
	  this.repository = repository;
	}

	public Article register(String boardname, String subject, String author, String content)
			throws ArticleRegistrationException {

	    Article newArticle = Article.create(boardname, subject, author, content);
	    repository.save(newArticle);
	    return newArticle;
	  }
}
