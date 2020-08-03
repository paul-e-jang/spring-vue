package bashpound.marketplace.web.payload;

import bashpound.marketplace.domain.application.commands.ChangeCardTitleCommand;
import bashpound.marketplace.domain.model.card.CardId;

public class ChangeCardTitlePayload {

  private String title;

  public ChangeCardTitleCommand toCommand(long cardId) {
    return new ChangeCardTitleCommand(new CardId(cardId), title);
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
