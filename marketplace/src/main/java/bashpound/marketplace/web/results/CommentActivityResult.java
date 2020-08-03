package bashpound.marketplace.web.results;

import org.springframework.http.ResponseEntity;

import bashpound.marketplace.domain.model.activity.Activity;

public class CommentActivityResult {

  public static ResponseEntity<ApiResult> build(Activity activity) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", activity.getId().value())
      .add("cardId", activity.getCardId().value())
      .add("boardId", activity.getBoardId().value())
      .add("userId", activity.getUserId().value())
      .add("type", activity.getType().getType())
      .add("detail", activity.getDetail())
      .add("createdDate", activity.getCreatedDate().getTime());
    return Result.ok(apiResult);
  }
}
