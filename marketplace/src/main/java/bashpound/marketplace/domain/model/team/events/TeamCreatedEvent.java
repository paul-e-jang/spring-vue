package bashpound.marketplace.domain.model.team.events;

import bashpound.marketplace.domain.common.event.DomainEvent;
import bashpound.marketplace.domain.common.event.TriggeredBy;
import bashpound.marketplace.domain.model.team.Team;
import bashpound.marketplace.domain.model.team.TeamId;

public class TeamCreatedEvent extends DomainEvent {

  private static final long serialVersionUID = 2714833255396717504L;

  private TeamId teamId;
  private String teamName;

  public TeamCreatedEvent(Team team, TriggeredBy triggeredBy) {
    super(triggeredBy);
    this.teamId = team.getId();
    this.teamName = team.getName();
  }

  public TeamId getTeamId() {
    return teamId;
  }

  public String getTeamName() {
    return teamName;
  }

  @Override
  public String toString() {
    return "TeamCreatedEvent{" +
      "teamId=" + teamId +
      ", teamName='" + teamName + '\'' +
      '}';
  }
}
