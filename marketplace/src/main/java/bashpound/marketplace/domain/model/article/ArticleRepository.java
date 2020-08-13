package bashpound.marketplace.domain.model.article;

import java.util.List;

public interface ArticleRepository {

  /**
   * Find board by its id
   *
   * @param articleId the id of the board
   * @return the article instance or null if it doesn't exist
   */
  Article findById(ArticleId articleId);
  
  /**
   * 
   * @param boardname the name of the board
   * @return articles
   */
  List<Article> findByBoardName(String boardname);

  /**
   * Save a board
   *
   * @param board the board to save
   */
  void save(Article article);
  void updateReplies(Long id, int i) throws ExecuteUpdateException;
  void updateViews(Long id, int i) throws ExecuteUpdateException;
}
