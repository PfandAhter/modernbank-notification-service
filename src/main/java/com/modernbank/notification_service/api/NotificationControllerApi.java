package com.modernbank.notification_service.api;

import com.modernbank.notification_service.api.request.BaseRequest;
import com.modernbank.notification_service.api.request.DeleteNotificationRequest;
import com.modernbank.notification_service.api.request.MarkAsReadNotificationRequest;
import com.modernbank.notification_service.api.response.GetNotificationsResponse;
import com.modernbank.notification_service.api.request.NotificationMessage;
import com.modernbank.notification_service.rest.response.BaseResponse;
import org.springframework.web.bind.annotation.*;


public interface NotificationControllerApi {

    @PostMapping(value = "/send", produces = "application/json", consumes = "application/json")
    BaseResponse sendNotification (@RequestBody NotificationMessage notificationMessage);

    @PostMapping(value = "/get")
    GetNotificationsResponse getAllNotifications(@RequestBody BaseRequest baseRequest);

    @PostMapping(value = "/read")
    BaseResponse markAsRead(@RequestBody MarkAsReadNotificationRequest request);

    @PostMapping(value = "/delete")
    BaseResponse deleteNotification(@RequestBody DeleteNotificationRequest request);
}
