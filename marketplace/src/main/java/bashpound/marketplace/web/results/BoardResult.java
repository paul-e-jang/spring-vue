package bashpound.marketplace.web.results;

import bashpound.marketplace.domain.common.file.FileUrlCreator;
import bashpound.marketplace.domain.model.board.Board;
import bashpound.marketplace.domain.model.user.Users;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardResult {

  public static ResponseEntity<ApiResult> build(Board board, List<Users> members,
                                                FileUrlCreator fileUrlCreator) {
    Map<String, Object> boardData = new HashMap<>();
    boardData.put("id", board.getId().value());
    boardData.put("name", board.getName());

    List<MemberData> membersData = new ArrayList<>();
    for (Users user: members) {
      membersData.add(new MemberData(user));
    }

    ApiResult result = ApiResult.blank()
      .add("board", boardData)
      .add("members", membersData);

    return Result.ok(result);
  }

  private static class MemberData {
    private long userId;
    private String shortName;
    private String name;

    MemberData(Users user) {
      this.userId = user.getId().value();
      this.shortName = user.getInitials();
      this.name = user.getFirstName() + " " + user.getLastName();
    }

    public long getUserId() {
      return userId;
    }

    public String getShortName() {
      return shortName;
    }

    public String getName() {
      return name;
    }
  }


}
