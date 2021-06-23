package com.world_of_anonymous.webflux_websocket;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/*
 WebSocketHandler will handle WebSocket messages and lifecycle events.
 EchoHandler will receive a message and return it prefixed with “RECEIVED ON SERVER ::”.
 */
public class EchoHandler implements WebSocketHandler {
  @Override
  public Mono<Void> handle(WebSocketSession session) {
    return session
        .send(session.receive()
            .map(webSocketMessage -> "RECEIVED ON SERVER :: "+webSocketMessage.getPayloadAsText())
            .map(session::textMessage)
        );
  }
}
