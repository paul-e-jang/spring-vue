package bashpound.marketplace.infrastructure.repository;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import bashpound.marketplace.domain.model.article.Article;
import bashpound.marketplace.domain.model.article.ArticleId;
import bashpound.marketplace.domain.model.article.ArticleRegistrationException;
import bashpound.marketplace.domain.model.article.ArticleRepository;
import bashpound.marketplace.domain.model.article.ExecuteUpdateException;

import java.util.List;

import javax.persistence.EntityManager;

@Repository
public class HibernateArticleRepository extends HibernateSupport implements ArticleRepository {

  public HibernateArticleRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public Article findById(ArticleId articleId) {
    Query<Article> query = getSession().createQuery("from article where id = :id", Article.class);
    query.setParameter("id", articleId.value());
    return query.uniqueResult();
  }
  
  @Override
  public List<Article> findByBoardName(String boardname) {
	  String sql = "select * from article where boardname = :boardname order by created_date desc";
	  NativeQuery<Article> query = getSession().createNativeQuery(sql, Article.class);
	  query.setParameter("boardname", boardname);
	  return query.list();
  } 

  @Override
  public void save(Article article) {
    entityManager.persist(article);
    entityManager.flush();
  }

  @Override
  public void updateReplies(Long id, int i) throws ExecuteUpdateException {
	  String sql = "update article set viewed=viewed+:i where id = :id ";
	  NativeQuery<Article> query = getSession().createNativeQuery(sql, Article.class);
	  query.setParameter("id", id);
	  query.setParameter("i", i);
	  query.executeUpdate();
}

  @Override
  public void updateViews(Long id, int i) throws ExecuteUpdateException {
	  String sql = "update article set replies=replies+:i where id = :id ";
	  NativeQuery<Article> query = getSession().createNativeQuery(sql, Article.class);
	  query.setParameter("id", id);
	  query.setParameter("i", i);
	  query.executeUpdate();
}

}
