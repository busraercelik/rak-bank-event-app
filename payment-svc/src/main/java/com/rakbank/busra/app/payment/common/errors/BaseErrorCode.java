package com.rakbank.busra.app.payment.common.errors;

public interface BaseErrorCode {
    String getCode();
    int getHttpStatus();
}
