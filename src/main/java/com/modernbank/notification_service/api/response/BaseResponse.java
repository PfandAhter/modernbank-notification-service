package com.modernbank.notification_service.api.response;

import com.modernbank.notification_service.constants.ErrorCodeConstants;
import com.modernbank.notification_service.constants.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BaseResponse {

    private String status = ResponseStatus.SUCCESS_CODE;

    private String processCode = ErrorCodeConstants.SUCCESS;

    private String processMessage = ResponseStatus.PROCESS_SUCCESS;

    public BaseResponse(String processMessage){
        this.processCode = ResponseStatus.PROCESS_SUCCESS;
        this.processMessage = processMessage;
    }
}