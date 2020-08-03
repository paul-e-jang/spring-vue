package bashpound.marketplace.web.payload;

import bashpound.marketplace.domain.application.commands.AddCardCommentCommand;
import bashpound.marketplace.domain.model.card.CardId;

public class AddCardCommentPayload {

  private String comment;

  public AddCardCommentCommand toCommand(CardId cardId) {
    return new AddCardCommentCommand(cardId, comment);
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
