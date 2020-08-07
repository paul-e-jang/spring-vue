package bashpound.marketplace.domain.model.article;

import bashpound.marketplace.domain.common.model.AbstractBaseId;

public class ArticleId extends AbstractBaseId {

  private static final long serialVersionUID = 6059316483218660038L;

  public ArticleId(long id) {
    super(id);
  }
  
  public String toString() {
	    return String.valueOf(value());
	  }
}
