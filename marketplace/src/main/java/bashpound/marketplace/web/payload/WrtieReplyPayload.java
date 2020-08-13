package bashpound.marketplace.web.payload;

import javax.validation.constraints.NotNull;
import bashpound.marketplace.domain.application.commands.WriteReplyCommand;

public class WrtieReplyPayload {

  @NotNull
  private String content;

  @NotNull
  private String author;
  
  @NotNull
  private int articlecode;

  public WriteReplyCommand toCommand() {
    return new WriteReplyCommand(this.author, this.content, this.articlecode);
  }

public String getContent() {
	return content;
}

public String getAuthor() {
	return author;
}

public int getArticlecode() {
	return articlecode;
}

public void setContent(String content) {
	this.content = content;
}

public void setAuthor(String author) {
	this.author = author;
}

public void setArticlecode(int articlecode) {
	this.articlecode = articlecode;
}


}
