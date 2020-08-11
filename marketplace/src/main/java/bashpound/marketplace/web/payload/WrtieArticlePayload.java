package bashpound.marketplace.web.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bashpound.marketplace.domain.application.commands.WriteArticleCommand;

public class WrtieArticlePayload {

  @NotNull
  private String boardname;
	
  @Size(min = 1, max = 50, message = "Subject must be between 2 and 50 characters")
  @NotNull
  private String subject;

  @NotNull
  private String content;

  @NotNull
  private String author;
  
  @NotNull
  private int viewed;

  public WriteArticleCommand toCommand() {
    return new WriteArticleCommand(this.boardname, this.subject, this.content, this.author, this.viewed);
  }

public String getBoardname() {
	return boardname;
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

public int getViewed() {
	return viewed;
}

public void setBoardname(String boardname) {
	this.boardname = boardname;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public void setContent(String content) {
	this.content = content;
}

public void setAuthor(String author) {
	this.author = author;
}

public void setViewed(int viewed) {
	this.viewed = viewed;
}






}
