package bashpound.marketplace.web.results;

import bashpound.marketplace.domain.model.board.Board;
import bashpound.marketplace.domain.model.user.Users;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDataResult {

  public static ResponseEntity<ApiResult> build(Users user, List<Board> boards,
                                                String realTimeServerUrl, String realTimeToken) {

    Map<String, Object> userData = new HashMap<>();
    userData.put("name", user.getFirstName() + " " + user.getLastName());
    userData.put("token", realTimeToken);

    Map<String, Object> settings = new HashMap<>();
    settings.put("realTimeServerUrl", realTimeServerUrl);


    List<BoardResult> boardResults = new ArrayList<>();
    for (Board board : boards) {
      boardResults.add(new BoardResult(board));
    }

    ApiResult apiResult = ApiResult.blank()
      .add("user", userData)
      .add("boards", boardResults)
      .add("settings", settings);

    return Result.ok(apiResult);
  }

  private static class BoardResult {
    private long id;
    private String name;
    private String description;

    BoardResult(Board board) {
      this.id = board.getId().value();
      this.name = board.getName();
      this.description = board.getDescription();
    }

    public long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public String getDescription() {
      return description;
    }
  }
}
