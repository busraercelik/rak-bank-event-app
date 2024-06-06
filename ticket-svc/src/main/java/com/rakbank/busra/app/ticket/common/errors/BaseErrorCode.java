package com.rakbank.busra.app.ticket.common.errors;

public interface BaseErrorCode {
    String getCode();
    int getHttpStatus();
}
