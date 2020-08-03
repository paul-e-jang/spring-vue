package bashpound.marketplace.infrastructure.repository;

import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import bashpound.marketplace.domain.model.board.BoardId;
import bashpound.marketplace.domain.model.board.BoardMember;
import bashpound.marketplace.domain.model.board.BoardMemberRepository;
import bashpound.marketplace.domain.model.user.Users;
import bashpound.marketplace.domain.model.user.UserId;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateBoardMemberRepository extends HibernateSupport<BoardMember> implements BoardMemberRepository {

  HibernateBoardMemberRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Users> findMembers(BoardId boardId) {
    String sql =
      " SELECT u.* FROM user u " +
      " LEFT JOIN board_member bm ON u.id = bm.user_id " +
      " WHERE bm.board_id = :boardId ";
    NativeQuery<Users> query = getSession().createNativeQuery(sql, Users.class);
    query.setParameter("boardId", boardId.value());
    return query.list();
  }

  @Override
  public void add(BoardId boardId, UserId userId) {
    String sql = "INSERT IGNORE INTO board_member (board_id, user_id) VALUE (:boardId, :userId)";
    NativeQuery query = getSession().createNativeQuery(sql);
    query.setParameter("boardId", boardId.value());
    query.setParameter("userId", userId.value());
    query.executeUpdate();
  }
}
