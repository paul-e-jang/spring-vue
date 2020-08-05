package bashpound.marketplace.web.payload;

import bashpound.marketplace.domain.application.commands.AddBoardMemberCommand;
import bashpound.marketplace.domain.model.board.BoardId;

public class AddBoardMemberPayload {

  private String usernameOrEmailAddress;

  public AddBoardMemberCommand toCommand(BoardId boardId) {
    return new AddBoardMemberCommand(boardId, usernameOrEmailAddress);
  }

  public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
    this.usernameOrEmailAddress = usernameOrEmailAddress;
  }
}
