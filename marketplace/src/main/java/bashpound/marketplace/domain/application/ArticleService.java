package bashpound.marketplace.domain.application;

import java.util.List;

import bashpound.marketplace.domain.application.commands.WriteArticleCommand;
import bashpound.marketplace.domain.application.commands.WriteReplyCommand;
import bashpound.marketplace.domain.model.article.Article;
import bashpound.marketplace.domain.model.article.ArticleLoadException;
import bashpound.marketplace.domain.model.article.ArticleRegistrationException;
import bashpound.marketplace.domain.model.article.ExecuteUpdateException;
import bashpound.marketplace.domain.model.article.Reply;

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
    * @param articlecode targets article pk
    * @return replies as ArticleReply List
    * @throws ArticleLoad when load failed.
    */
	List<Reply> findRepliesByArticleCode(Long id) throws ArticleLoadException;

	/**
    * Register a new article with subject, author, content
    *
    * @param registerCommand instance of <code>RegistrationCommand</code>
    * @throws ArticleRegistrationException when registration failed. Possible reasons are:
    *                               1) Null content
    */	
	void articleRegister(WriteArticleCommand writeArticleCommand) throws ArticleRegistrationException;
	void replyRegister(WriteReplyCommand writeReplyCommand) throws ArticleRegistrationException;
	void updateReplies(Long id, int i) throws ExecuteUpdateException;
	void updateViews(Long id, int i) throws ExecuteUpdateException;
  
}
