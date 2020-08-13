package bashpound.marketplace.domain.model.article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Replies {
	
    private List<Reply> replies = new ArrayList<>();

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public void addReplies(Collection<Reply> replies) {
        this.replies.addAll(replies);
    }

}
