package bashpound.marketplace.domain.model.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import bashpound.marketplace.domain.common.event.DomainEvent;
import bashpound.marketplace.domain.model.attachment.events.CardAttachmentAddedEvent;
import bashpound.marketplace.domain.model.board.events.BoardCreatedEvent;
import bashpound.marketplace.domain.model.board.events.BoardMemberAddedEvent;
import bashpound.marketplace.domain.model.card.events.CardAddedEvent;
import bashpound.marketplace.domain.model.card.events.CardDescriptionChangedEvent;
import bashpound.marketplace.domain.model.card.events.CardTitleChangedEvent;
import bashpound.marketplace.domain.model.cardlist.events.CardListAddedEvent;

@Component
public class DomainEventToActivityConverter {

  private static final Logger log = LoggerFactory.getLogger(DomainEventToActivityConverter.class);

  /**
   * Convert a domain event to the corresponding activity
   *
   * @param event a domain event
   * @return a corresponding activity, or null when no activity tracked by that domain event
   */
  public Activity toActivity(DomainEvent event) {
    if (event instanceof BoardCreatedEvent) {
      return BoardActivities.from((BoardCreatedEvent) event);
    } else if (event instanceof BoardMemberAddedEvent) {
      return BoardActivities.from((BoardMemberAddedEvent) event);
    } else if (event instanceof CardAttachmentAddedEvent) {
      return CardActivities.from((CardAttachmentAddedEvent) event);
    } else if (event instanceof CardAddedEvent) {
      return CardActivities.from((CardAddedEvent) event);
    } else if (event instanceof CardDescriptionChangedEvent) {
      return CardActivities.from((CardDescriptionChangedEvent) event);
    } else if (event instanceof CardTitleChangedEvent) {
      return CardActivities.from((CardTitleChangedEvent) event);
    } else if (event instanceof CardListAddedEvent) {
      return CardListActivities.from((CardListAddedEvent) event);
    }

    log.debug("No activity converted from " + event);
    return null;
  }
}
