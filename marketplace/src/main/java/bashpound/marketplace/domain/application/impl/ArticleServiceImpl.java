package bashpound.marketplace.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import bashpound.marketplace.domain.application.ArticleService;
import bashpound.marketplace.domain.application.commands.WriteArticleCommand;
import bashpound.marketplace.domain.common.event.DomainEventPublisher;
import bashpound.marketplace.domain.model.article.Article;
import bashpound.marketplace.domain.model.article.ArticleLoadException;
import bashpound.marketplace.domain.model.article.ArticleRegistrationException;
import bashpound.marketplace.domain.model.article.ArticleRegistrationManagement;
import bashpound.marketplace.domain.model.article.ArticleRepository;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	  private ArticleRegistrationManagement articleRegistrationManagement;
	  private DomainEventPublisher domainEventPublisher;
	  private ArticleRepository articleRepository;

	  public ArticleServiceImpl(ArticleRegistrationManagement articleRegistrationManagement,
	                         DomainEventPublisher domainEventPublisher,
	                         ArticleRepository articleRepository) {
	    this.articleRegistrationManagement = articleRegistrationManagement;
	    this.domainEventPublisher = domainEventPublisher;
	    this.articleRepository = articleRepository;
	  }

	  @Override
	  public void register(WriteArticleCommand command) throws ArticleRegistrationException {
	    Assert.notNull(command, "Parameter `command` must not be null");
	    articleRegistrationManagement.register(
	      command.getBoardname(),
	      command.getAuthor(),
	      command.getSubject(),
	      command.getContent());
	  }

	  @Override
	  public List<Article> findByBoardName(String boardname) throws ArticleLoadException{
		return articleRepository.findByBoardName(boardname);
	  }
	  

}