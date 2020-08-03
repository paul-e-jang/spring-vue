package bashpound.marketplace.domain.model.attachment.events;

import bashpound.marketplace.domain.common.event.TriggeredBy;
import bashpound.marketplace.domain.model.attachment.Attachment;
import bashpound.marketplace.domain.model.attachment.AttachmentId;
import bashpound.marketplace.domain.model.card.Card;
import bashpound.marketplace.domain.model.card.events.CardDomainEvent;

public class CardAttachmentAddedEvent extends CardDomainEvent {

  private static final long serialVersionUID = -7962885726212050836L;

  private String cardTitle;
  private AttachmentId attachmentId;
  private String fileName;

  public CardAttachmentAddedEvent(Card card, Attachment attachment, TriggeredBy triggeredBy) {
    super(card.getId(), card.getTitle(), card.getBoardId(), triggeredBy);
    this.cardTitle = card.getTitle();
    this.attachmentId = attachment.getId();
    this.fileName = attachment.getFileName();
  }

  public String getCardTitle() {
    return cardTitle;
  }

  public AttachmentId getAttachmentId() {
    return attachmentId;
  }

  public String getFileName() {
    return fileName;
  }

  @Override
  public String toString() {
    return "CardAttachmentAddedEvent{" +
      "cardId=" + getCardId() +
      ", cardTitle='" + cardTitle + '\'' +
      ", attachmentId=" + attachmentId +
      ", fileName='" + fileName + '\'' +
      '}';
  }
}
