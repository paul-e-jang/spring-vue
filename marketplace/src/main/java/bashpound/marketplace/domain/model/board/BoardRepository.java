package bashpound.marketplace.domain.model.board;

import bashpound.marketplace.domain.model.user.UserId;

import java.util.List;

public interface BoardRepository {

  /**
   * Find the boards that a user is a member, including those boards
   * the user created as well as joined.
   *
   * @param userId the id of the user
   * @return a list of boards or an empty list if none found
   */
  List<Board> findBoardsByMembership(UserId userId);

  /**
   * Find board by its id
   *
   * @param boardId the id of the board
   * @return the board instance or null if it doesn't exist
   */
  Board findById(BoardId boardId);

  /**
   * Save a board
   *
   * @param board the board to save
   */
  void save(Board board);
}
