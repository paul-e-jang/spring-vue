package bashpound.marketplace.domain.model.article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Articles {
	
    private List<Article> articles = new ArrayList<>();

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void addArticles(Collection<Article> articles) {
        this.articles.addAll(articles);
    }

}
