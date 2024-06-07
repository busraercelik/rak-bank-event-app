package com.rakbank.busra.app.user.errors;

import com.rakbank.busra.app.user.common.errors.BaseErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {
    INTERNAL_SERVER_ERROR("ERROR_GEN_500", 500),
    USER_NOT_FOUND("ERROR_USER_100", 404),
    USER_ALREADY_REGISTERED("ERROR_USER_200", 404);

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
