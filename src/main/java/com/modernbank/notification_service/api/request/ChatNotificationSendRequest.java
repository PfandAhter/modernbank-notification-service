package com.modernbank.notification_service.api.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ChatNotificationSendRequest {
    private String message;
    private String type;
    private String title;
    private String userId;
    private String level;
    private Map<String, Object> arguments;

    private LocalDateTime time;
}