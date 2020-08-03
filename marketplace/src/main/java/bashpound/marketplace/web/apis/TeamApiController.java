package bashpound.marketplace.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bashpound.marketplace.domain.application.TeamService;
import bashpound.marketplace.domain.application.commands.CreateTeamCommand;
import bashpound.marketplace.domain.model.team.Team;
import bashpound.marketplace.web.payload.CreateTeamPayload;
import bashpound.marketplace.web.results.ApiResult;
import bashpound.marketplace.web.results.CreateTeamResult;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeamApiController extends AbstractBaseController {

  private TeamService teamService;

  public TeamApiController(TeamService teamService) {
    this.teamService = teamService;
  }

  @PostMapping("/api/teams")
  public ResponseEntity<ApiResult> createTeam(@RequestBody CreateTeamPayload payload,
                                              HttpServletRequest request) {
    CreateTeamCommand command = payload.toCommand();
    addTriggeredBy(command, request);

    Team team = teamService.createTeam(command);
    return CreateTeamResult.build(team);
  }
}
