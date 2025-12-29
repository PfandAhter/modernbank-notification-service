package com.modernbank.notification_service.rest.service.events.producer;


import com.modernbank.notification_service.api.request.ChatNotificationSendRequest;
import com.modernbank.notification_service.api.request.ForceLogoutUserRequest;
import com.modernbank.notification_service.api.request.NotificationMessage;
import com.modernbank.notification_service.api.request.SetMaintenanceModeRequest;
import com.modernbank.notification_service.model.ChatNotificationModel;
import com.modernbank.notification_service.rest.service.MapperService;
import com.modernbank.notification_service.rest.service.events.ISendNotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendNotificationProducerImpl implements ISendNotificationProducer {

    private final KafkaTemplate<String, NotificationMessage> notificationKafkaTemplate;

    private final KafkaTemplate<String, ChatNotificationModel> chatNotificationModelKafkaTemplate;

    private final MapperService mapperService;

    @Override
    public void produceNotificationSend(NotificationMessage notificationMessage) {
        notificationKafkaTemplate.send("notification-send", notificationMessage);
        log.info("Sent notification message to Kafka topic");
    }

    @Override
    public void sendChatNotificationMessage(ChatNotificationSendRequest request) {
        ChatNotificationModel model = mapperService.map(request, ChatNotificationModel.class);
        model.setUserId(request.getUserId());// Ensure userId is set in the model from the request otherwise it might be null
        chatNotificationModelKafkaTemplate.send("chat-notification-send", model);
        log.info("Sent chat notification message to Kafka topic");
    }

    @Override
    public void setMaintenanceMode(SetMaintenanceModeRequest request) {
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setType("MAINTENANCE");
        notificationMessage.setTitle(String.valueOf(request.isMaintenanceMode()).toUpperCase());
        notificationMessage.setMessage(request.getMessage());
        notificationMessage.setUserId("BROADCAST");

        notificationKafkaTemplate.send("notification-send-no-persist", notificationMessage);
    }

    @Override
    public void forceLogoutUser(ForceLogoutUserRequest request) {
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setType("FORCE_LOGOUT");
        notificationMessage.setUserId(request.getLogoutUserId());
        notificationMessage.setMessage(request.getReason());

        notificationKafkaTemplate.send("notification-send-no-persist", notificationMessage);
    }
}