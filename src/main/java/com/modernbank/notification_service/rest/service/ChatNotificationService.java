package com.modernbank.notification_service.rest.service;

import com.modernbank.notification_service.api.request.ChatNotificationSendRequest;

public interface ChatNotificationService {
    void sendChatNotification(ChatNotificationSendRequest request);
}