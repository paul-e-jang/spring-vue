package bashpound.marketplace.infrastructure.repository;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import bashpound.marketplace.domain.model.article.Article;
import bashpound.marketplace.domain.model.article.ArticleId;
import bashpound.marketplace.domain.model.article.ArticleRepository;

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
	  String sql = "select * from article where boardname = :boardname";
	  NativeQuery<Article> query = getSession().createNativeQuery(sql, Article.class);
	  query.setParameter("boardname", boardname);
	  return query.list();
  } 

  @Override
  public void save(Article article) {
    entityManager.persist(article);
    entityManager.flush();
  }

}
