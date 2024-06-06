package com.rakbank.busra.app.user.errors;

import com.rakbank.busra.app.user.common.errors.BaseErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {
    INTERNAL_SERVER_ERROR("ERROR_GEN_500", 500),
    EVENT_NOT_FOUND("ERROR_EVENT_100", 404),
    INVALID_DATE_TIME_FORMAT("ERROR_EVENT_200", 400),
    ;

    final String code;
    final int httpStatus;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public int getHttpStatus() {
        return this.httpStatus;
    }
}
