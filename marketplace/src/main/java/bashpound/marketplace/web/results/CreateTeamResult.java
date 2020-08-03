package bashpound.marketplace.web.results;

import org.springframework.http.ResponseEntity;

import bashpound.marketplace.domain.model.team.Team;

public class CreateTeamResult {

  public static ResponseEntity<ApiResult> build(Team team) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", team.getId().value())
      .add("name", team.getName());
    return Result.ok(apiResult);
  }
}
