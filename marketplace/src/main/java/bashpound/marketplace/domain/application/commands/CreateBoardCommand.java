package bashpound.marketplace.domain.application.commands;

import bashpound.marketplace.domain.model.team.TeamId;

public class CreateBoardCommand extends UserCommand {

  private String name;
  private String description;
  private TeamId teamId;

  public CreateBoardCommand(String name, String description, TeamId teamId) {
    this.name = name;
    this.description = description;
    this.teamId = teamId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public TeamId getTeamId() {
    return teamId;
  }
}
