package com.modernbank.notification_service.api.response;

import com.modernbank.notification_service.api.dto.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GetNotificationsResponse extends BaseResponse {

    List<NotificationDTO> notifications;
}