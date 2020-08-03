package bashpound.marketplace.infrastructure.repository;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import bashpound.marketplace.domain.model.user.Users;
import bashpound.marketplace.domain.model.user.UserId;
import bashpound.marketplace.domain.model.user.UserRepository;

import javax.persistence.EntityManager;

@Repository
public class HibernateUserRepository extends HibernateSupport<Users> implements UserRepository {

  public HibernateUserRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public Users findByUsername(String username) {
    Query<Users> query = getSession().createQuery("from User where username = :username", Users.class);
    query.setParameter("username", username);
    return query.uniqueResult();
  }

  @Override
  public Users findByEmailAddress(String emailAddress) {
    Query<Users> query = getSession().createQuery("from User where emailAddress = :emailAddress", Users.class);
    query.setParameter("emailAddress", emailAddress);
    return query.uniqueResult();
  }

  @Override
  public Users findById(UserId userId) {
    Query<Users> query = getSession().createQuery("from User where id = :id", Users.class);
    query.setParameter("id", userId.value());
    return query.uniqueResult();
  }
}
