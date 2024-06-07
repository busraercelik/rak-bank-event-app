package com.rakbank.busra.app.payment.errors;

import com.rakbank.busra.app.payment.common.errors.BaseErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {
    INTERNAL_SERVER_ERROR("ERROR_GEN_500", 500),
    PAYMENT_FAILED("ERROR_EVENT_100", 400),
    DUPLICATE_TRANSACTION("ERROR_EVENT_100", 409),
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
