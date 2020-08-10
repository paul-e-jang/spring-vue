package bashpound.marketplace.domain.model.article;

import java.util.List;

public interface ArticleRepository {

  /**
   * Find board by its id
   *
   * @param boardId the id of the board
   * @return the board instance or null if it doesn't exist
   */
  Article findById(ArticleId articleId);
  
  /**
   * 
   * @param boardname
   * @return select * from articles where boardname = boardname
   */
  List<Article> findByBoardName(String boardname);

  /**
   * Save a board
   *
   * @param board the board to save
   */
  void save(Article article);
}
