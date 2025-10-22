package com.modernbank.notification_service.rest.service.events.consumer;

import com.modernbank.notification_service.entity.Notification;
import com.modernbank.notification_service.repository.NotificationRepository;
import com.modernbank.notification_service.api.request.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendNotificationConsumer {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "notification-send", groupId = "notification-service", containerFactory = "sendNotificationKafkaListenerContainerFactory")
    public void consumeNotificationSend(NotificationMessage notificationMessage){
        Map<String,Object> notification = new HashMap<>();

        notification.put("id", UUID.randomUUID().toString());
        notification.put("title", notificationMessage.getTitle());
        notification.put("type" , notificationMessage.getType());
        notification.put("message", notificationMessage.getMessage());

        notificationRepository.save(Notification.builder()
                .userId(notificationMessage.getUserId())
                .title(notificationMessage.getTitle())
                .type(notificationMessage.getType())
                .message(notificationMessage.getMessage())
                .timestamp(LocalDateTime.now())
                .isRead(false)
                .isDeleted(false)
                .build());

        log.info("Sending notification to user: {}", notificationMessage.getUserId());
        simpMessagingTemplate.convertAndSend("/user/"+ notificationMessage.getUserId() + "/notifications", notification);
    }


    @KafkaListener(topics = "chat-notification-send", groupId = "chat-notification-service", containerFactory = "sendChatNotificationKafkaListenerContainerFactory")
    public void consumeChatNotificationSend(com.modernbank.notification_service.model.ChatNotificationModel chatNotificationModel){
        log.info("Sending chat notification to user: {}", chatNotificationModel.getUserId());
        simpMessagingTemplate.convertAndSend("/user/chat/model/" + chatNotificationModel.getUserId() + "/notifications", chatNotificationModel);
    }
}