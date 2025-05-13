package com.modernbank.notification_service.rest.controller.api;

import com.modernbank.notification_service.rest.controller.request.NotificationMessage;
import com.modernbank.notification_service.rest.controller.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface NotificationControllerApi {

    @PostMapping(value = "/send", produces = "application/json", consumes = "application/json")
    ResponseEntity<BaseResponse> sendNotification (@RequestBody NotificationMessage notificationMessage);

}
