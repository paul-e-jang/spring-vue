package bashpound.marketplace.web.payload;

import bashpound.marketplace.domain.application.commands.ChangeCardDescriptionCommand;
import bashpound.marketplace.domain.model.card.CardId;

public class ChangeCardDescriptionPayload {

  private String description;

  public ChangeCardDescriptionCommand toCommand(long cardId) {
    return new ChangeCardDescriptionCommand(new CardId(cardId), description);
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
