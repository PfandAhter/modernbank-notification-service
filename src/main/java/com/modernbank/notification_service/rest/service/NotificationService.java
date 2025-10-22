package com.modernbank.notification_service.rest.service;

import com.modernbank.notification_service.model.NotificationModel;

import java.util.List;

public interface NotificationService {
    List<NotificationModel> getUserNotifications(String userId);

    void markNotificationAsRead(Long notificationId);

    void deleteNotification(Long notificationId);
}
