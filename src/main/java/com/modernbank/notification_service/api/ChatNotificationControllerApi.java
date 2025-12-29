package com.modernbank.notification_service.api;

import com.modernbank.notification_service.api.request.ChatNotificationSendRequest;
import com.modernbank.notification_service.api.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ChatNotificationControllerApi {

    @PostMapping(path = "/send")
    BaseResponse sendChatNotification(@RequestBody ChatNotificationSendRequest request);
}