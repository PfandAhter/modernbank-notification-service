package com.modernbank.notification_service.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // enableSimpleBroker ile direkt publish-subscribe mantığı sağlanır
        registry.enableSimpleBroker("/user"); // /user kanalına doğrudan mesaj göndereceğiz
        registry.setApplicationDestinationPrefixes("/app"); // Client'ın mesaj gönderdiği endpoint prefix
        // setUserDestinationPrefix GEREK YOK çünkü convertAndSendToUser() kullanmıyoruz
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry
                .addEndpoint("/notification-websocket")
                .setAllowedOriginPatterns("http://localhost:8080", "http://localhost:3000")
                .withSockJS();


        registry
                .addEndpoint("/chat-websocket")
                .setAllowedOriginPatterns("http://localhost:8080", "http://localhost:3000")
                .withSockJS();
    }
}