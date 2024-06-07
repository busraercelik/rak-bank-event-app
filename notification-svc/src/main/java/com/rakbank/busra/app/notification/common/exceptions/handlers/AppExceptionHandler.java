package com.rakbank.busra.app.notification.common.exceptions.handlers;

import com.rakbank.busra.app.notification.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.notification.common.exceptions.ApplicationException;
import com.rakbank.busra.app.notification.errors.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public BaseAPIResponse<?> handleError(ApplicationException ex) {
        log.error("ApplicationException occurred : code {} detail : {}",
                ex.getErrorCode(), ex.getDetail(), ex);
        return new BaseAPIResponse<>(ex.getErrorCode().getCode(), ex.getDetail(), ex.getDetail());
    }

    @ExceptionHandler(Throwable.class)
    public BaseAPIResponse<?> handleUnknownError(Throwable ex) {
        log.error("{} occurred - {}", ex.getClass(), ex.getMessage(), ex);
        return new BaseAPIResponse<>(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage(), ex.getMessage());
    }
}
