package bashpound.marketplace.domain.application.commands;

import java.util.Objects;

import org.springframework.util.Assert;

public class WriteReplyCommand extends AnonymousCommand{
	private String author;
	private String content;
	private int articlecode;

	 public WriteReplyCommand(String author, String content, int articlecode) {
		Assert.hasText(content, "Parameter `content` must not be empty");
		Assert.hasText(author, "Parameter `author` must not be empty");
		Assert.hasText(articlecode+"", "Parameter `viewed` must not be empty");
		
		this.author = author;
	    this.content = content;
	    this.articlecode = articlecode;
	  }


	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public int getArticlecode() {
		return articlecode;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public void setArticlecode(int articlecode) {
		this.articlecode = articlecode;
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
	      ", articlecode='" + articlecode + '\'' +
	      '}';
	  }	
	

}
