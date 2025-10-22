package com.modernbank.notification_service.exceptions;

import lombok.Getter;

public class NotFoundException extends RuntimeException {
    @Getter
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
