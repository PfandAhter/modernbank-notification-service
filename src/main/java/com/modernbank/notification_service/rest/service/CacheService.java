package com.modernbank.notification_service.rest.service;

import com.modernbank.notification_service.entity.ErrorCodes;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public interface CacheService {
    void getErrorCodes();

    ConcurrentHashMap<String, ErrorCodes> getErrorCodesList();
}
