package bashpound.marketplace.infrastructure.repository;

import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import bashpound.marketplace.domain.model.activity.Activity;
import bashpound.marketplace.domain.model.activity.ActivityRepository;
import bashpound.marketplace.domain.model.card.CardId;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateActivityRepository extends HibernateSupport<Activity> implements ActivityRepository {

  HibernateActivityRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Activity> findCardActivities(CardId cardId) {
    String sql = "SELECT a.* FROM activity a WHERE a.card_id = :cardId order by id desc";
    NativeQuery<Activity> query = getSession().createNativeQuery(sql, Activity.class);
    query.setParameter("cardId", cardId.value());
    return query.list();
  }
}
