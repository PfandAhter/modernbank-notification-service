package com.modernbank.notification_service.rest.service.events.producer;


import com.modernbank.notification_service.rest.controller.request.NotificationMessage;
import com.modernbank.notification_service.rest.controller.response.BaseResponse;
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

    @Override
    public BaseResponse produceNotificationSend(NotificationMessage notificationMessage){
        log.info("Sending notification message to Kafka topic");
        notificationKafkaTemplate.send("notification-send",notificationMessage);
        return new BaseResponse("Notification sent successfully");
    }
}