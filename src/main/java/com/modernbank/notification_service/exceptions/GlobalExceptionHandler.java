package com.modernbank.notification_service.exceptions;

import com.modernbank.notification_service.api.dto.ErrorCodesDTO;
import com.modernbank.notification_service.api.response.BaseResponse;
import com.modernbank.notification_service.entity.ErrorCodes;
import com.modernbank.notification_service.rest.service.CacheService;
import com.modernbank.notification_service.rest.service.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final MapperService mapperService;

    private final CacheService cacheService;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseResponse> handleException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createFailResponse(e.getMessage()));
    }

    private BaseResponse createFailResponse(String exceptionMessage){
        ErrorCodesDTO errorCodesDTO = findErrorCode(exceptionMessage);
        return new BaseResponse(errorCodesDTO.getId(),errorCodesDTO.getError(),errorCodesDTO.getDescription());
    }

    private ErrorCodesDTO findErrorCode(String error){
        ErrorCodes errorCodes = cacheService.getErrorCodesList().get(error);

        if (errorCodes == null) {
            errorCodes = new ErrorCodes();
            errorCodes.setId("FAILED");
            errorCodes.setError(error);
            errorCodes.setDescription(error);
        }

        return mapperService.map(errorCodes, ErrorCodesDTO.class);
    }
}