package bashpound.marketplace.domain.application.commands;

public class CreateBoardCommand extends UserCommand {

  private String name;
  private String description;

  public CreateBoardCommand(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

}