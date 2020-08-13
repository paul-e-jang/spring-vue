package bashpound.marketplace.domain.model.article;

import org.springframework.stereotype.Component;

@Component
public class ArticleRegistrationManagement {

	private ArticleRepository articleRepository;
	private ReplyRepository replyRepository;

	public ArticleRegistrationManagement(ArticleRepository articleRepository, ReplyRepository replyRepository) {
	  this.articleRepository = articleRepository;
	  this.replyRepository = replyRepository;
	}
	
	public Article articleRegister(String boardname, String subject, String author, String content, int viewed)
			throws ArticleRegistrationException {

	    Article newArticle = Article.create(boardname, subject, author, content, viewed);
	    articleRepository.save(newArticle);
	    return newArticle;
	  }
	
	public Reply replyRegister(String author, String content, int articlecode)
			throws ArticleRegistrationException {
		
	    Reply newReply = Reply.create(author, content, articlecode);
	    replyRepository.save(newReply);
	    return newReply;
	  }
}
