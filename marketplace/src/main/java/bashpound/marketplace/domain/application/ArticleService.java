package bashpound.marketplace.domain.application;

import java.util.List;

import bashpound.marketplace.domain.application.commands.WriteArticleCommand;
import bashpound.marketplace.domain.model.article.Article;
import bashpound.marketplace.domain.model.article.ArticleLoadException;
import bashpound.marketplace.domain.model.article.ArticleRegistrationException;

public interface ArticleService {
	
	/**
	* Find Article by boardname
	*
	* @param boardname the name of the board
	* @return articles list to be parced into jsonformat
	 * @throws ArticleLoadException 
	*/
	List<Article> findByBoardName(String boardname) throws ArticleLoadException;
	
	/**
    * Register a new article with subject, author, content
    *
    * @param registerCommand instance of <code>RegistrationCommand</code>
    * @throws ArticleRegistrationException when registration failed. Possible reasons are:
    *                               1) Null content
    */
	void register(WriteArticleCommand writeArticleCommand) throws ArticleRegistrationException;
  

  
}
