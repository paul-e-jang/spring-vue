package bashpound.marketplace.domain.application;

import java.util.List;

import bashpound.marketplace.domain.application.commands.AddCardListCommand;
import bashpound.marketplace.domain.application.commands.ChangeCardListPositionsCommand;
import bashpound.marketplace.domain.model.board.BoardId;
import bashpound.marketplace.domain.model.cardlist.CardList;

public interface CardListService {

  /**
   * Find card lists of a board
   *
   * @param boardId id of the board
   * @return a list of card list instance or an empty list if none found
   */
  List<CardList> findByBoardId(BoardId boardId);

  /**
   * Add card list
   *
   * @param command the command instance
   * @return the newly added card list
   */
  CardList addCardList(AddCardListCommand command);

  /**
   * Change card list positions
   *
   * @param command the command instance
   */
  void changePositions(ChangeCardListPositionsCommand command);
}
