package com.modernbank.notification_service.rest.service.impl;

import com.modernbank.notification_service.api.request.ChatNotificationSendRequest;
import com.modernbank.notification_service.rest.service.ChatNotificationService;
import com.modernbank.notification_service.rest.service.events.ISendNotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatNotificationServiceImpl implements ChatNotificationService {

    private final ISendNotificationProducer sendChatNotificationProducer;

    @Override
    public void sendChatNotification(ChatNotificationSendRequest request) {
        sendChatNotificationProducer.sendChatNotificationMessage(request);
        log.info("Chat notification sent: {}", request);
    }
}