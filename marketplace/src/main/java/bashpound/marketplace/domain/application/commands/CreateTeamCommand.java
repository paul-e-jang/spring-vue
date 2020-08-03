package bashpound.marketplace.domain.application.commands;

public class CreateTeamCommand extends UserCommand {

  private String name;

  public CreateTeamCommand(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
