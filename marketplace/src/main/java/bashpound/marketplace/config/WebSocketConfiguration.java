package bashpound.marketplace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import bashpound.marketplace.web.socket.WebSocketRequestDispatcher;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

  private WebSocketRequestDispatcher requestDispatcher;

  public WebSocketConfiguration(WebSocketRequestDispatcher requestDispatcher) {
    this.requestDispatcher = requestDispatcher;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(requestDispatcher, "/rt").setAllowedOrigins("*").withSockJS();
  }
}
