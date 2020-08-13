package bashpound.marketplace.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import bashpound.marketplace.domain.application.ArticleService;
import bashpound.marketplace.domain.application.commands.WriteArticleCommand;
import bashpound.marketplace.domain.application.commands.WriteReplyCommand;
import bashpound.marketplace.domain.common.event.DomainEventPublisher;
import bashpound.marketplace.domain.model.article.Article;
import bashpound.marketplace.domain.model.article.ArticleLoadException;
import bashpound.marketplace.domain.model.article.ArticleRegistrationException;
import bashpound.marketplace.domain.model.article.ArticleRegistrationManagement;
import bashpound.marketplace.domain.model.article.Reply;
import bashpound.marketplace.domain.model.article.ReplyRepository;
import bashpound.marketplace.domain.model.article.ArticleRepository;
import bashpound.marketplace.domain.model.article.ExecuteUpdateException;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	  private ArticleRegistrationManagement articleRegistrationManagement;
	  private DomainEventPublisher domainEventPublisher;
	  private ArticleRepository articleRepository;
	  private ReplyRepository replyRepository;

	  public ArticleServiceImpl(ArticleRegistrationManagement articleRegistrationManagement,
	                         DomainEventPublisher domainEventPublisher,
	                         ArticleRepository articleRepository,
	                         ReplyRepository replyRepository) {
	    this.articleRegistrationManagement = articleRegistrationManagement;
	    this.domainEventPublisher = domainEventPublisher;
	    this.articleRepository = articleRepository;
	    this.replyRepository = replyRepository;
	  }

	  @Override
	  public void articleRegister(WriteArticleCommand command) throws ArticleRegistrationException {
	    Assert.notNull(command, "Parameter `command` must not be null");
	    articleRegistrationManagement.articleRegister(
	      command.getBoardname(),
	      command.getAuthor(),
	      command.getSubject(),
	      command.getContent(),
	      command.getViewed());
	  }
	  
	  @Override
	  public void replyRegister(WriteReplyCommand command) throws ArticleRegistrationException {
	    Assert.notNull(command, "Parameter `command` must not be null");
	    articleRegistrationManagement.replyRegister(
	      command.getAuthor(),
	      command.getContent(),
	      command.getArticleId()
	      );
		}

	  @Override
	  public List<Article> findByBoardName(String boardname) throws ArticleLoadException{
		return articleRepository.findByBoardName(boardname);
	  }

	  @Override
	  public List<Reply> findRepliesByArticleCode(Long id) throws ArticleLoadException {
		return replyRepository.findByArticleCode(id);
	  }

	@Override
	public void updateReplies(Long id, int i) throws ExecuteUpdateException {
		articleRepository.updateReplies(id, i);
	}

	@Override
	public void updateViews(Long id, int i) throws ExecuteUpdateException {
		articleRepository.updateViews(id, i);
	}


	  

}