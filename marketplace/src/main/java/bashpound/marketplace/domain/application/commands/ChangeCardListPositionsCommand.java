package bashpound.marketplace.domain.application.commands;

import java.util.List;

import bashpound.marketplace.domain.model.board.BoardId;
import bashpound.marketplace.domain.model.cardlist.CardListPosition;

public class ChangeCardListPositionsCommand extends UserCommand {

  private BoardId boardId;
  private List<CardListPosition> cardListPositions;

  public ChangeCardListPositionsCommand(BoardId boardId, List<CardListPosition> cardListPositions) {
    this.boardId = boardId;
    this.cardListPositions = cardListPositions;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public List<CardListPosition> getCardListPositions() {
    return cardListPositions;
  }
}
