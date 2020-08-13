package bashpound.marketplace.domain.application.commands;

import java.util.Objects;

import org.springframework.util.Assert;

public class WriteReplyCommand extends AnonymousCommand{
	private String author;
	private String content;
	private Long articleId;

	 public WriteReplyCommand(String author, String content, Long articleId) {
		Assert.hasText(content, "Parameter `content` must not be empty");
		Assert.hasText(author, "Parameter `author` must not be empty");
		Assert.hasText(articleId+"", "Parameter `viewed` must not be empty");
		
		this.author = author;
	    this.content = content;
	    this.articleId = articleId;
	  }


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Long getArticleId() {
		return articleId;
	}


	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}


	@Override
	  public int hashCode() {
	    return Objects.hash(author, content);
	  }

	  @Override
	  public String toString() {
	    return "WriteReplyCommand{" +
	      ", author='" + author + '\'' +
	      ", content='" + content + '\'' +
	      ", articleId='" + articleId + '\'' +
	      '}';
	  }
	

}
