package bashpound.marketplace.domain.model.article;

public interface ArticleRepository {

  /**
   * Find board by its id
   *
   * @param boardId the id of the board
   * @return the board instance or null if it doesn't exist
   */
  Article findById(ArticleId articleId);

  /**
   * Save a board
   *
   * @param board the board to save
   */
  void save(Article article);
}
