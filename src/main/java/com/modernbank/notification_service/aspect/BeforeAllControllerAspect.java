package com.modernbank.notification_service.aspect;

import com.modernbank.notification_service.api.request.BaseRequest;
import com.modernbank.notification_service.rest.service.HeaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor

public class BeforeAllControllerAspect {

    private final HeaderService headerService;

    @Before(value = "execution(* com.modernbank.notification_service.controller.*.*(..))")
    public void setTokenBeforeController(JoinPoint joinPoint){
        Object[] parameters = joinPoint.getArgs();
        for(Object param : parameters){
            if(param instanceof BaseRequest baseRequest){
                String userId = headerService.extractUserId();
                String userRole = headerService.extractUserRole();
                String userEmail = headerService.extractUserEmail();
                baseRequest.setUserId(userId);
                baseRequest.setUserRole(userRole);
                baseRequest.setUserEmail(userEmail);
            }
        }
    }
}