package com.modernbank.notification_service.rest.service.impl;

import com.modernbank.notification_service.entity.Notification;
import com.modernbank.notification_service.exceptions.NotFoundException;
import com.modernbank.notification_service.model.NotificationModel;
import com.modernbank.notification_service.repository.NotificationRepository;
import com.modernbank.notification_service.rest.service.MapperService;
import com.modernbank.notification_service.rest.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.modernbank.notification_service.constants.ErrorCodeConstants.NOTIFICATION_NOT_FOUND;

@Service
@RequiredArgsConstructor

public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final MapperService mapperService;

    @Override
    public List<NotificationModel> getUserNotifications(String userId){
        List<Notification> notifications = notificationRepository.findAllByUserIdAndIsReadFalse(userId)
                .orElseThrow(() -> new NotFoundException(NOTIFICATION_NOT_FOUND));

        return mapperService.map(notifications,NotificationModel.class);
    }

    @Override
    public void markNotificationAsRead(Long notificationId){
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException(NOTIFICATION_NOT_FOUND));
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long notificationId){
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException(NOTIFICATION_NOT_FOUND));
        notification.setDeleted(true);
        notificationRepository.save(notification);
    }
}