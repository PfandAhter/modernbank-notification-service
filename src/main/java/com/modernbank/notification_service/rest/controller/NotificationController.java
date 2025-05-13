package com.modernbank.notification_service.rest.controller;

import com.modernbank.notification_service.rest.controller.api.NotificationControllerApi;
import com.modernbank.notification_service.rest.controller.request.NotificationMessage;
import com.modernbank.notification_service.rest.controller.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j

public class NotificationController implements NotificationControllerApi {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public ResponseEntity<BaseResponse> sendNotification(@RequestBody NotificationMessage message) {
        // WebSocket ile /topic/notifications kanalına mesaj gönder
        Map<String,Object> notification = new HashMap<>(); //TODO: BURAYI KAFKAYA YERLESTIREREK DAHA GUVENLI ISLEM YAPILMASINI SAGLA.

        notification.put("id", UUID.randomUUID().toString());
        notification.put("title", message.getTitle());
        notification.put("type" , message.getType());
        notification.put("message", message.getMessage());

//        messagingTemplate.convertAndSendToUser(message.getUserId(), "/queue/notifications", notification);
        messagingTemplate.convertAndSend("/user/6bb91e57-032c-40db-b6ce-4e3ef459c3a0/notifications", notification);

        return ResponseEntity.ok(new BaseResponse("Notification sent successfully"));
    }
}