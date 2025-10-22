package com.modernbank.notification_service.rest.service.impl;

import com.modernbank.notification_service.entity.ErrorCodes;
import com.modernbank.notification_service.repository.ErrorCodeRepository;
import com.modernbank.notification_service.rest.service.CacheService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j

public class CacheServiceImpl implements CacheService {

    private static final ConcurrentHashMap<String,ErrorCodes> errorCodeList = new ConcurrentHashMap<>();


    private final ErrorCodeRepository errorCodeRepository;

    @PostConstruct
    @Override
    public void getErrorCodes() {
        try {
            List<ErrorCodes> errorCodes = errorCodeRepository.findAll();
            errorCodes.forEach(errorCode -> errorCodeList.put(errorCode.getId(), errorCode));

        } catch (Exception e) {
            log.error("Error in getting error codes from database");
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshErrorCodes() {
        getErrorCodes();
    }

    @Override
    public ConcurrentHashMap<String, ErrorCodes> getErrorCodesList() {
        return errorCodeList;
    }
}
