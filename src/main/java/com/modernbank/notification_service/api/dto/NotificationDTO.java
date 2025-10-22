package com.modernbank.notification_service.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO {
    private Long id;

    private String userId;

    private String title;

    private String message;

    private String type;

    private String timestamp;

    private boolean isRead;

        private boolean isDeleted;
}