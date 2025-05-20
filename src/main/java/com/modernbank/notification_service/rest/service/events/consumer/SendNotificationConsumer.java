package com.modernbank.notification_service.rest.service.events.consumer;

import com.modernbank.notification_service.rest.controller.request.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendNotificationConsumer {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics = "notification-send", groupId = "notification-service", containerFactory = "kafkaListenerContainerFactory")
    public void consumeNotificationSend(NotificationMessage notificationMessage){
        Map<String,Object> notification = new HashMap<>();

        notification.put("id", UUID.randomUUID().toString());
        notification.put("title", notificationMessage.getTitle());
        notification.put("type" , notificationMessage.getType());
        notification.put("message", notificationMessage.getMessage());

//        messagingTemplate.convertAndSendToUser(message.getUserId(), "/queue/notifications", notification);
        log.info("Sending notification to user: {}", notificationMessage.getUserId());
        simpMessagingTemplate.convertAndSend("/user/6bb91e57-032c-40db-b6ce-4e3ef459c3a0/notifications", notification);
    }
}