package com.modernbank.notification_service.rest.service.events;

import com.modernbank.notification_service.api.request.ChatNotificationSendRequest;
import com.modernbank.notification_service.api.request.ForceLogoutUserRequest;
import com.modernbank.notification_service.api.request.NotificationMessage;
import com.modernbank.notification_service.api.request.SetMaintenanceModeRequest;

public interface ISendNotificationProducer {
    void produceNotificationSend(NotificationMessage notificationMessage);

    void sendChatNotificationMessage(ChatNotificationSendRequest request);

    void setMaintenanceMode(SetMaintenanceModeRequest request);

    void forceLogoutUser(ForceLogoutUserRequest request);
}
