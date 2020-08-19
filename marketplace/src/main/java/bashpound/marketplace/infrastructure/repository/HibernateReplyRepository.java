package bashpound.marketplace.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import bashpound.marketplace.domain.model.article.Article;
import bashpound.marketplace.domain.model.article.Reply;
import bashpound.marketplace.domain.model.article.ReplyRepository;

@Repository
public class HibernateReplyRepository extends HibernateSupport implements ReplyRepository {

  public HibernateReplyRepository(EntityManager entityManager) {
    super(entityManager);
  }


  @Override
  public void save(Reply articleReply) {
    entityManager.persist(articleReply);
    entityManager.flush();
  }

  @Override
  public List<Reply> findByArticleCode(Long id) {    
	String sql = "select * from reply where articleid = :articleid";
	NativeQuery<Reply> query = getSession().createNativeQuery(sql, Reply.class);
    query.setParameter("articleid", id);
    return query.list();
}

}
