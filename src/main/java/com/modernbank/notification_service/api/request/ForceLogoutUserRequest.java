package com.modernbank.notification_service.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForceLogoutUserRequest extends BaseRequest{
    private String logoutUserId;

    private String reason;
}