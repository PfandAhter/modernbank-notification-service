package com.modernbank.notification_service.api.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ChatNotificationSendRequest extends BaseRequest {
    private String message;
    private String type;
    private Map<String, Object> arguments;

    private LocalDateTime time;
}