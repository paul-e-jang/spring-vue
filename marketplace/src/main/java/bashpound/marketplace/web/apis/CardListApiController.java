package bashpound.marketplace.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bashpound.marketplace.domain.application.CardListService;
import bashpound.marketplace.domain.application.commands.AddCardListCommand;
import bashpound.marketplace.domain.application.commands.ChangeCardListPositionsCommand;
import bashpound.marketplace.domain.model.cardlist.CardList;
import bashpound.marketplace.web.payload.AddCardListPayload;
import bashpound.marketplace.web.payload.ChangeCardListPositionsPayload;
import bashpound.marketplace.web.results.AddCardListResult;
import bashpound.marketplace.web.results.ApiResult;
import bashpound.marketplace.web.results.Result;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CardListApiController extends AbstractBaseController {

  private CardListService cardListService;

  public CardListApiController(CardListService cardListService) {
    this.cardListService = cardListService;
  }

  @PostMapping("/api/card-lists")
  public ResponseEntity<ApiResult> addCardList(@RequestBody AddCardListPayload payload,
                                               HttpServletRequest request) {
    AddCardListCommand command = payload.toCommand();
    addTriggeredBy(command, request);

    CardList cardList = cardListService.addCardList(command);
    return AddCardListResult.build(cardList);
  }

  @PostMapping("/api/card-lists/positions")
  public ResponseEntity<ApiResult> changeCardListPositions(@RequestBody ChangeCardListPositionsPayload payload,
                                                           HttpServletRequest request) {
    ChangeCardListPositionsCommand command = payload.toCommand();
    addTriggeredBy(command, request);

    cardListService.changePositions(command);
    return Result.ok();
  }
}
