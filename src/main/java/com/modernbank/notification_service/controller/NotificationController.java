package com.modernbank.notification_service.controller;

import com.modernbank.notification_service.api.dto.NotificationDTO;
import com.modernbank.notification_service.api.request.*;
import com.modernbank.notification_service.api.response.GetNotificationsResponse;
import com.modernbank.notification_service.api.NotificationControllerApi;

import com.modernbank.notification_service.api.response.BaseResponse;
import com.modernbank.notification_service.rest.service.MapperService;
import com.modernbank.notification_service.rest.service.NotificationService;
import com.modernbank.notification_service.rest.service.events.ISendNotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@Slf4j

public class NotificationController implements NotificationControllerApi {

    private final ISendNotificationProducer sendNotificationProducer;
    private final NotificationService notificationService;
    private final MapperService mapperService;

    @Override
    public BaseResponse sendNotification(NotificationMessage message) {
        sendNotificationProducer.produceNotificationSend(message);
        return new BaseResponse("Notification sent successfully");
    }

    @Override
    public GetNotificationsResponse getAllNotifications(BaseRequest baseRequest) {
        return new GetNotificationsResponse(mapperService.map(notificationService.getUserNotifications(baseRequest.getUserId()), NotificationDTO.class));
    }

    @Override
    public BaseResponse markAsRead(MarkAsReadNotificationRequest request) {
        notificationService.markNotificationAsRead(request.getNotificationId());
        return new BaseResponse("Notification marked as read successfully");
    }

    @Override
    public BaseResponse deleteNotification(DeleteNotificationRequest request) {
        notificationService.deleteNotification(request.getNotificationId());
        return new BaseResponse("Notification deleted successfully");
    }

    @Override
    public BaseResponse setMaintenanceMode(SetMaintenanceModeRequest request) {
        sendNotificationProducer.setMaintenanceMode(request);
        return new BaseResponse("Maintenance mode updated successfully");
    }

    @Override
    public BaseResponse forceLogoutUser(ForceLogoutUserRequest request) {
        sendNotificationProducer.forceLogoutUser(request);
        return new BaseResponse("User logout forced successfully");
    }
}