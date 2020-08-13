package bashpound.marketplace.domain.model.article;

import java.util.List;

public interface ReplyRepository {


  /**
   * 
   * @param boardname the name of the board
   * @return articles
   */
  List<Reply> findByArticleCode(String articleCode);

  /**
   * Save a board
   *
   * @param board the board to save
   */
  void save(Reply articleReply);
 
  
}
