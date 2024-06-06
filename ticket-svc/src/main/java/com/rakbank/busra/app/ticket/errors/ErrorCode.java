package com.rakbank.busra.app.ticket.errors;
import com.rakbank.busra.app.ticket.common.errors.BaseErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {
    INTERNAL_SERVER_ERROR("ERROR_GEN_500", 500),
    TICKET_TYPE_NOT_FOUND("ERROR_TICKET_100", 404),
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
