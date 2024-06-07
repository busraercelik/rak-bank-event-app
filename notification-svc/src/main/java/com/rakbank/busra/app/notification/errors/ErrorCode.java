package com.rakbank.busra.app.notification.errors;

import com.rakbank.busra.app.notification.common.errors.BaseErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {
    INTERNAL_SERVER_ERROR("ERROR_GEN_500", 500),
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
