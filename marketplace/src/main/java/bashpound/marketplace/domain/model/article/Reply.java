package bashpound.marketplace.domain.model.article;

import bashpound.marketplace.domain.common.model.AbstractBaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reply")
public class Reply extends AbstractBaseEntity {

	private static final long serialVersionUID = 1671277594137915020L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "author", nullable = true)
  private String author;
  
  @Column(name = "content")
  private String content;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "createddate", nullable = false)
  private Date createddate;
  
  @Column(name="articlecode")
  private int articlecode;
  
  @Column(name="replycode")
  private int replycode;
  
  public Reply() {
  }

  /**
   * new Article publish
   */
  public static Reply create(String author, String content, int articlecode) {
    Reply articleReply = new Reply();
    articleReply.author = author;
    articleReply.content = content;
    articleReply.createddate = new Date();
    articleReply.articlecode = articlecode;
    articleReply.replycode = articleReply.hashCode();
    return articleReply;
  }


public Long getId() {
	return id;
}

public String getAuthor() {
	return author;
}

public String getContent() {
	return content;
}

public Date getCreatedDate() {
	return createddate;
}

public int getArticlecode() {
	return articlecode;
}

public int getReplycode() {
	return replycode;
}

@Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Reply)) return false;
    Reply articleReply = (Reply) o;
    return id == articleReply.id &&
      Objects.equals(id, articleReply.id);
  }


  @Override
  public String toString() {
    return "ArticleReply{" +
      "id=" + id +
      ", author='" + author + '\'' +
      ", content=" + content +
      ", createdDate=" + createddate +
      ", articlecode=" + articlecode +
      ", replycode=" + replycode +
      '}';
  }

@Override
public int hashCode() {
	return Objects.hash(this.id);
}
}
