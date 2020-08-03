package bashpound.marketplace.web.payload;

import java.util.List;

import bashpound.marketplace.domain.application.commands.ChangeCardPositionsCommand;
import bashpound.marketplace.domain.model.board.BoardId;
import bashpound.marketplace.domain.model.card.CardPosition;

public class ChangeCardPositionsPayload {

  private long boardId;
  private List<CardPosition> cardPositions;

  public ChangeCardPositionsCommand toCommand() {
    return new ChangeCardPositionsCommand(new BoardId(boardId), cardPositions);
  }

  public void setBoardId(long boardId) {
    this.boardId = boardId;
  }

  public void setCardPositions(List<CardPosition> cardPositions) {
    this.cardPositions = cardPositions;
  }
}
