package bashpound.marketplace.infrastructure.repository;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import bashpound.marketplace.domain.model.board.Board;
import bashpound.marketplace.domain.model.board.BoardId;
import bashpound.marketplace.domain.model.board.BoardRepository;
import bashpound.marketplace.domain.model.user.UserId;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateBoardRepository extends HibernateSupport<Board> implements BoardRepository {

  HibernateBoardRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Board> findBoardsByMembership(UserId userId) {
    String sql = "SELECT b.* FROM board b LEFT JOIN board_member bm ON b.id = bm.board_id WHERE bm.user_id = :userId";
    NativeQuery<Board> query = getSession().createNativeQuery(sql, Board.class);
    query.setParameter("userId", userId.value());
    return query.list();
  }

  @Override
  public Board findById(BoardId boardId) {
    Query<Board> query = getSession().createQuery("from Board where id = :id", Board.class);
    query.setParameter("id", boardId.value());
    return query.uniqueResult();
  }
}
