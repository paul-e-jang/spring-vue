package bashpound.marketplace.domain.model.cardlist.events;

import bashpound.marketplace.domain.common.event.TriggeredBy;
import bashpound.marketplace.domain.model.board.events.BoardDomainEvent;
import bashpound.marketplace.domain.model.cardlist.CardList;
import bashpound.marketplace.domain.model.cardlist.CardListId;

public class CardListAddedEvent extends BoardDomainEvent {

  private static final long serialVersionUID = -877934435476435188L;

  private CardListId cardListId;
  private String cardListName;

  public CardListAddedEvent(CardList cardList, TriggeredBy triggeredBy) {
    super(cardList.getBoardId(), triggeredBy);
    this.cardListId = cardList.getId();
    this.cardListName = cardList.getName();
  }

  public CardListId getCardListId() {
    return cardListId;
  }

  public String getCardListName() {
    return cardListName;
  }

  @Override
  public String toString() {
    return "CardListAddedEvent{" +
      "cardListId=" + cardListId +
      ", cardListName='" + cardListName + '\'' +
      '}';
  }
}
