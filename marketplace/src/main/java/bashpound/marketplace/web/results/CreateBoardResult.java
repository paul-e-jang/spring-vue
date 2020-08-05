package bashpound.marketplace.web.results;

import bashpound.marketplace.domain.model.board.Board;
import org.springframework.http.ResponseEntity;

public class CreateBoardResult {

  public static ResponseEntity<ApiResult> build(Board board) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", board.getId().value())
      .add("name", board.getName())
      .add("description", board.getDescription());
    return Result.ok(apiResult);
  }
}
