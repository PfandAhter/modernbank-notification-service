package com.modernbank.notification_service.controller;

import com.modernbank.notification_service.api.ChatNotificationControllerApi;
import com.modernbank.notification_service.api.request.ChatNotificationSendRequest;
import com.modernbank.notification_service.rest.response.BaseResponse;
import com.modernbank.notification_service.rest.service.ChatNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/notification/chat")
public class ChatNotificationController implements ChatNotificationControllerApi {

    private final ChatNotificationService chatNotificationService;

    @Override
    public BaseResponse sendChatNotification(ChatNotificationSendRequest request) {
        chatNotificationService.sendChatNotification(request);
        return new BaseResponse("Chat notification sent successfully");
    }
}