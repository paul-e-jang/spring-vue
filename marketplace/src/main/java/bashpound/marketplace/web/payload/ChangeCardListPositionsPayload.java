package bashpound.marketplace.web.payload;

import java.util.List;

import bashpound.marketplace.domain.application.commands.ChangeCardListPositionsCommand;
import bashpound.marketplace.domain.model.board.BoardId;
import bashpound.marketplace.domain.model.cardlist.CardListPosition;

public class ChangeCardListPositionsPayload {

  private long boardId;
  private List<CardListPosition> cardListPositions;

  public ChangeCardListPositionsCommand toCommand() {
    return new ChangeCardListPositionsCommand(new BoardId(boardId), cardListPositions);
  }

  public void setBoardId(long boardId) {
    this.boardId = boardId;
  }

  public void setCardListPositions(List<CardListPosition> cardListPositions) {
    this.cardListPositions = cardListPositions;
  }
}
