package bashpound.marketplace.web.socket.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bashpound.marketplace.web.socket.*;

@ChannelHandler("/board/*")
public class BoardChannelHandler {

  private static final Logger log = LoggerFactory.getLogger(BoardChannelHandler.class);

  @Action("subscribe")
  public void subscribe(RealTimeSession session, @ChannelValue String channel) {
    log.debug("RealTimeSession[{}] Subscribe to channel `{}`", session.id(), channel);
    SubscriptionHub.subscribe(session, channel);
  }

  @Action("unsubscribe")
  public void unsubscribe(RealTimeSession session, @ChannelValue String channel) {
    log.debug("RealTimeSession[{}] Unsubscribe from channel `{}`", session.id(), channel);
    SubscriptionHub.unsubscribe(session, channel);
  }
}
