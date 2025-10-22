package com.modernbank.notification_service.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BaseRequest {

    @JsonIgnore
    private LocalDateTime time = LocalDateTime.now();

    @JsonIgnore
    private String token;

    private String userId;

    private String userRole;

    private String userEmail;
}