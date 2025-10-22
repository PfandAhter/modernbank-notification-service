package com.modernbank.notification_service.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ErrorCodesDTO {

    private String id;

    private String error;

    private String description;
}