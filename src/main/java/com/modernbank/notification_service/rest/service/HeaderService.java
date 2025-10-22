package com.modernbank.notification_service.rest.service;

public interface HeaderService {
    String extractToken();

    String extractUserEmail();

    String extractUserId();

    String extractUserRole();
}