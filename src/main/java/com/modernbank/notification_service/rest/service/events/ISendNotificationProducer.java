package com.modernbank.notification_service.rest.service.events;

import com.modernbank.notification_service.api.request.ChatNotificationSendRequest;
import com.modernbank.notification_service.api.request.NotificationMessage;

public interface ISendNotificationProducer {
    void produceNotificationSend(NotificationMessage notificationMessage);

    void sendChatNotificationMessage(ChatNotificationSendRequest request);
}
