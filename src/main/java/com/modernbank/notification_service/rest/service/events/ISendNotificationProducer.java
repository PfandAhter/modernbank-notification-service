package com.modernbank.notification_service.rest.service.events;

import com.modernbank.notification_service.rest.controller.request.NotificationMessage;
import com.modernbank.notification_service.rest.controller.response.BaseResponse;

public interface ISendNotificationProducer {
    BaseResponse produceNotificationSend(NotificationMessage notificationMessage);
}
