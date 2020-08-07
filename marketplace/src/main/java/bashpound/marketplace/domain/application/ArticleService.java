package bashpound.marketplace.domain.application;

import bashpound.marketplace.domain.application.commands.WriteArticleCommand;
import bashpound.marketplace.domain.model.article.ArticleRegistrationException;

public interface ArticleService {

  /**
   * Register a new article with subject, author, content
   *
   * @param registerCommand instance of <code>RegistrationCommand</code>
   * @throws ArticleRegistrationException when registration failed. Possible reasons are:
   *                               1) Null content
   */
  void register(WriteArticleCommand writeArticleCommand) throws ArticleRegistrationException;
  

  
}
