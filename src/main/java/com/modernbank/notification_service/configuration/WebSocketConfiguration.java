package com.modernbank.notification_service.configuration;


import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /*@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic"); // /queue kullanıcıya özel
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user"); // 👈 bu önemli!
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry
                .addEndpoint("/notification-websocket")
                .setAllowedOriginPatterns("http://localhost:3000") // For Cors
                .withSockJS();
    }*/

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
                .addEndpoint("/notification-websocket") // Client bu endpoint'e bağlanacak
                .setAllowedOriginPatterns("http://localhost:3000") // React CORS izni
                .withSockJS(); // Geriye uyumlu bağlantı için
    }
}