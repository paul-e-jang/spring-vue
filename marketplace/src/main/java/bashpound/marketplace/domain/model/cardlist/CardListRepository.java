package bashpound.marketplace.domain.model.cardlist;

import java.util.List;

import bashpound.marketplace.domain.model.board.BoardId;

public interface CardListRepository {

  /**
   * Find a card list by its id
   *
   * @param cardListId the id of the card list
   * @return a card list instance or null if not found
   */
  CardList findById(CardListId cardListId);

  /**
   * Find card lists of a board
   *
   * @param boardId the id of the board
   * @return a list of card lists of a board or an empty list if none found
   */
  List<CardList> findByBoardId(BoardId boardId);

  /**
   * Save card list
   *
   * @param cardList cardList to save
   */
  void save(CardList cardList);

  /**
   * Change card list positions
   *
   * @param cardListPositions the positions of card lists
   */
  void changePositions(List<CardListPosition> cardListPositions);
}
