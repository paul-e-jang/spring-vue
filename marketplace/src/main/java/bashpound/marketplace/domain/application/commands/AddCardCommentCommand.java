package bashpound.marketplace.domain.application.commands;

import bashpound.marketplace.domain.model.card.CardId;

public class AddCardCommentCommand extends UserCommand {

  private CardId cardId;
  private String comment;

  public AddCardCommentCommand(CardId cardId, String comment) {
    this.cardId = cardId;
    this.comment = comment;
  }

  public CardId getCardId() {
    return cardId;
  }

  public String getComment() {
    return comment;
  }
}
