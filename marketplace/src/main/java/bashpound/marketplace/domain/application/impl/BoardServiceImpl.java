package bashpound.marketplace.domain.application.impl;

import org.springframework.stereotype.Service;

import bashpound.marketplace.domain.application.BoardService;
import bashpound.marketplace.domain.application.commands.AddBoardMemberCommand;
import bashpound.marketplace.domain.application.commands.CreateBoardCommand;
import bashpound.marketplace.domain.common.event.DomainEventPublisher;
import bashpound.marketplace.domain.model.board.*;
import bashpound.marketplace.domain.model.board.events.BoardCreatedEvent;
import bashpound.marketplace.domain.model.board.events.BoardMemberAddedEvent;
import bashpound.marketplace.domain.model.user.Users;
import bashpound.marketplace.domain.model.user.UserFinder;
import bashpound.marketplace.domain.model.user.UserId;
import bashpound.marketplace.domain.model.user.UserNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

  private BoardRepository boardRepository;
  private BoardManagement boardManagement;
  private BoardMemberRepository boardMemberRepository;
  private UserFinder userFinder;
  private DomainEventPublisher domainEventPublisher;

  public BoardServiceImpl(BoardRepository boardRepository,
                          BoardManagement boardManagement,
                          BoardMemberRepository boardMemberRepository,
                          UserFinder userFinder,
                          DomainEventPublisher domainEventPublisher) {
    this.boardRepository = boardRepository;
    this.boardManagement = boardManagement;
    this.boardMemberRepository = boardMemberRepository;
    this.userFinder = userFinder;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public List<Board> findBoardsByMembership(UserId userId) {
    return boardRepository.findBoardsByMembership(userId);
  }

  @Override
  public Board findById(BoardId boardId) {
    return boardRepository.findById(boardId);
  }

  @Override
  public List<Users> findMembers(BoardId boardId) {
    return boardMemberRepository.findMembers(boardId);
  }

  @Override
  public Board createBoard(CreateBoardCommand command) {
    Board board = boardManagement.createBoard(command.getUserId(), command.getName(),
      command.getDescription(), command.getTeamId());
    domainEventPublisher.publish(new BoardCreatedEvent(board, command));
    return board;
  }

  @Override
  public Users addMember(AddBoardMemberCommand command) throws UserNotFoundException {
    Users user = userFinder.find(command.getUsernameOrEmailAddress());
    boardMemberRepository.add(command.getBoardId(), user.getId());
    domainEventPublisher.publish(new BoardMemberAddedEvent(command.getBoardId(), user, command));
    return user;
  }
}
