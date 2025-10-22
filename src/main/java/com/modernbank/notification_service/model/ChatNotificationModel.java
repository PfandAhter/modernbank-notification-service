package com.modernbank.notification_service.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ChatNotificationModel {
    private String userId;
    private String message;
    private String title;
    private String type;
    private Map<String, Object> arguments;

    private LocalDateTime time;
}