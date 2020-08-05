package bashpound.marketplace.web.payload;

import bashpound.marketplace.domain.application.commands.CreateBoardCommand;

public class CreateBoardPayload {

  private String name;
  private String description;

  public CreateBoardCommand toCommand() {
    return new CreateBoardCommand(name, description);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
