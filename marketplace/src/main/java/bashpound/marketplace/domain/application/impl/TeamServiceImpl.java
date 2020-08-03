package bashpound.marketplace.domain.application.impl;

import org.springframework.stereotype.Service;

import bashpound.marketplace.domain.application.TeamService;
import bashpound.marketplace.domain.application.commands.CreateTeamCommand;
import bashpound.marketplace.domain.common.event.DomainEventPublisher;
import bashpound.marketplace.domain.model.team.Team;
import bashpound.marketplace.domain.model.team.TeamId;
import bashpound.marketplace.domain.model.team.TeamRepository;
import bashpound.marketplace.domain.model.team.events.TeamCreatedEvent;
import bashpound.marketplace.domain.model.user.UserId;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

  private TeamRepository teamRepository;
  private DomainEventPublisher domainEventPublisher;

  public TeamServiceImpl(TeamRepository teamRepository, DomainEventPublisher domainEventPublisher) {
    this.teamRepository = teamRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public List<Team> findTeamsByUserId(UserId userId) {
    return teamRepository.findTeamsByUserId(userId);
  }

  @Override
  public Team findById(TeamId teamId) {
    return teamRepository.findById(teamId);
  }

  @Override
  public Team createTeam(CreateTeamCommand command) {
    Team team = Team.create(command.getName(), command.getUserId());
    teamRepository.save(team);
    domainEventPublisher.publish(new TeamCreatedEvent(team, command));
    return team;
  }
}
