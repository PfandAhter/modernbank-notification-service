package com.modernbank.notification_service.configuration;

import com.modernbank.notification_service.rest.controller.request.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConfiguration {

    //SEND NOTIFICATION KAFKA

    @Bean
    public ProducerFactory<String, NotificationMessage> sendNotificationProducerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, NotificationMessage> sendNotificationKafkaTemplate(){
//        KafkaTemplate<String, WithdrawAndDepositMoneyRequest> kafkaTemplate = new KafkaTemplate<>(moneyWithdrawAndDepositProducerFactory());
        return new KafkaTemplate<>(sendNotificationProducerFactory());
    }

    @Bean
    public ConsumerFactory<String, NotificationMessage> sendNotificationConsumerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-send");
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.modernbank.notification_service.rest.controller.request");
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.modernbank.notification_service.rest.controller.request.NotificationMessage");

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(NotificationMessage.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationMessage> sendNotificationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NotificationMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(sendNotificationConsumerFactory());
        // DefaultErrorHandler ile ilişkilendir
        factory.setCommonErrorHandler(defaultErrorHandler());
        return factory;
    }

    @Bean
    public DefaultErrorHandler defaultErrorHandler(){
        FixedBackOff fixedBackOff = new FixedBackOff(3000L,0);

        return new DefaultErrorHandler(fixedBackOff);
    }
}
