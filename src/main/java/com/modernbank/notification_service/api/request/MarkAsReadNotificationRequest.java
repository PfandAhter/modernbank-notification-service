package com.modernbank.notification_service.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MarkAsReadNotificationRequest {
    private Long notificationId;
}