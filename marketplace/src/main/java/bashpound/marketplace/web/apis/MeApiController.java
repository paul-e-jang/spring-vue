package bashpound.marketplace.web.apis;

import bashpound.marketplace.domain.application.BoardService;
import bashpound.marketplace.domain.application.UserService;
import bashpound.marketplace.domain.common.security.CurrentUser;
import bashpound.marketplace.domain.common.security.TokenManager;
import bashpound.marketplace.domain.model.board.Board;
import bashpound.marketplace.domain.model.user.SimpleUser;
import bashpound.marketplace.domain.model.user.Users;
import bashpound.marketplace.web.results.ApiResult;
import bashpound.marketplace.web.results.MyDataResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MeApiController {

  private String realTimeServerUrl;
  private BoardService boardService;
  private UserService userService;
  private TokenManager tokenManager;

  public MeApiController(@Value("${app.real-time-server-url}") String realTimeServerUrl,
                         BoardService boardService,
                         UserService userService,
                         TokenManager tokenManager) {
    this.realTimeServerUrl = realTimeServerUrl;
    this.boardService = boardService;
    this.userService = userService;
    this.tokenManager = tokenManager;
  }

  @GetMapping("/api/me")
  public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
    Users user = userService.findById(currentUser.getUserId());
    List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
    String realTimeToken = tokenManager.jwt(user.getId());
    return MyDataResult.build(user, boards, realTimeServerUrl, realTimeToken);
  }
}
