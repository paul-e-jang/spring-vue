package bashpound.marketplace.domain.model.board.events;

import bashpound.marketplace.domain.common.event.DomainEvent;
import bashpound.marketplace.domain.common.event.TriggeredBy;
import bashpound.marketplace.domain.model.board.BoardId;

public abstract class BoardDomainEvent extends DomainEvent {

  private static final long serialVersionUID = -147308556973863979L;

  private BoardId boardId;

  public BoardDomainEvent(BoardId boardId, TriggeredBy triggeredBy) {
    super(triggeredBy);
    this.boardId = boardId;
  }

  public BoardId getBoardId() {
    return boardId;
  }
}
