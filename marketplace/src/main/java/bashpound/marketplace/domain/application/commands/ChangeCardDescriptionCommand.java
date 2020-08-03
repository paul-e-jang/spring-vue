package bashpound.marketplace.domain.application.commands;

import bashpound.marketplace.domain.model.card.CardId;

public class ChangeCardDescriptionCommand extends UserCommand {

  private CardId cardId;
  private String description;

  public ChangeCardDescriptionCommand(CardId cardId, String description) {
    this.cardId = cardId;
    this.description = description;
  }

  public CardId getCardId() {
    return cardId;
  }

  public String getDescription() {
    return description;
  }
}
