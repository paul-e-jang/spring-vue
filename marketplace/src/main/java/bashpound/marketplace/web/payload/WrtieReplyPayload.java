package bashpound.marketplace.web.payload;

import javax.validation.constraints.NotNull;
import bashpound.marketplace.domain.application.commands.WriteReplyCommand;

public class WrtieReplyPayload {

  @NotNull
  private String content;

  @NotNull
  private String author;
  
  @NotNull
  private Long articleId;

  public WriteReplyCommand toCommand() {
    return new WriteReplyCommand(this.author, this.content, this.articleId);
  }

public String getContent() {
	return content;
}

public String getAuthor() {
	return author;
}

public Long getArticleId() {
	return articleId;
}

public void setContent(String content) {
	this.content = content;
}

public void setAuthor(String author) {
	this.author = author;
}

public void setArticlecode(Long articleId) {
	this.articleId = articleId;
}


}
