package bashpound.marketplace.web.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bashpound.marketplace.domain.application.commands.WriteArticleCommand;

public class WrtieArticlePayload {

  @Size(min = 1, max = 50, message = "Subject must be between 2 and 50 characters")
  @NotNull
  private String subject;

  @NotNull
  private String content;

  @NotNull
  private String author;

  public WriteArticleCommand toCommand() {
    return new WriteArticleCommand(this.subject, this.content, this.author);
  }

public String getSubject() {
	return subject;
}

public String getContent() {
	return content;
}

public String getAuthor() {
	return author;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public void setContent(String content) {
	this.content = content;
}

public void setAuther(String author) {
	this.author = author;
}


}
