package com.modernbank.notification_service.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationMessage {
    private String userId;

    private String message;

    private String type;

    private String title;
}