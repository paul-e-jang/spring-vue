package bashpound.marketplace.infrastructure.repository;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import bashpound.marketplace.domain.model.article.Article;
import bashpound.marketplace.domain.model.article.ArticleId;
import bashpound.marketplace.domain.model.article.ArticleRepository;

import javax.persistence.EntityManager;

@Repository
public class HibernateArticleRepository extends HibernateSupport implements ArticleRepository {

  public HibernateArticleRepository(EntityManager entityManager) {
    super(entityManager);
  }

  
  @Override
  public Article findById(ArticleId articleId) {
    Query<Article> query = getSession().createQuery("from Article where id = :id", Article.class);
    query.setParameter("id", articleId.value());
    return query.uniqueResult();
  }
 

  @Override
  public void save(Article article) {
    entityManager.persist(article);
    entityManager.flush();
  }


}
