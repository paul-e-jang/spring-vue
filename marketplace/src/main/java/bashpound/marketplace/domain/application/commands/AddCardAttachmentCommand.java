package bashpound.marketplace.domain.application.commands;

import org.springframework.web.multipart.MultipartFile;

import bashpound.marketplace.domain.model.card.CardId;

public class AddCardAttachmentCommand extends UserCommand {

  private CardId cardId;
  private MultipartFile file;

  public AddCardAttachmentCommand(long cardId, MultipartFile file) {
    this.cardId = new CardId(cardId);
    this.file = file;
  }

  public CardId getCardId() {
    return cardId;
  }

  public MultipartFile getFile() {
    return file;
  }
}
