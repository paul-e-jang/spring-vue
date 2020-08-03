package bashpound.marketplace.web.results;

import org.springframework.http.ResponseEntity;

import bashpound.marketplace.domain.model.card.Card;

public class CardResult {

  public static ResponseEntity<ApiResult> build(Card card) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", card.getId().value())
      .add("boardId", card.getBoardId().value())
      .add("cardListId", card.getCardListId().value())
      .add("title", card.getTitle())
      .add("description", card.getDescription());
    return Result.ok(apiResult);
  }

}
