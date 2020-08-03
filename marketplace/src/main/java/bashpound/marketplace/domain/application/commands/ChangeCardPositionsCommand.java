package bashpound.marketplace.domain.application.commands;

import java.util.List;

import bashpound.marketplace.domain.model.board.BoardId;
import bashpound.marketplace.domain.model.card.CardPosition;

public class ChangeCardPositionsCommand extends UserCommand {

  private BoardId boardId;
  private List<CardPosition> cardPositions;

  public ChangeCardPositionsCommand(BoardId boardId, List<CardPosition> cardPositions) {
    this.boardId = boardId;
    this.cardPositions = cardPositions;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public List<CardPosition> getCardPositions() {
    return cardPositions;
  }
}
