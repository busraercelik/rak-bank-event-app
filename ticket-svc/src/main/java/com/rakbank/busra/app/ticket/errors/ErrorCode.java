package com.rakbank.busra.app.ticket.errors;
import com.rakbank.busra.app.ticket.common.errors.BaseErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {
    INTERNAL_SERVER_ERROR("ERROR_GEN_500", 500),
    TICKET_TYPE_NOT_FOUND("ERROR_TICKET_100", 404),
    TICKET_EVENT_INVENTORY_NOT_FOUND("ERROR_TICKET_200", 404),
    TICKET_EVENT_INVENTORY_NO_TICKET_TYPE("ERROR_TICKET_300", 400),
    TICKET_NOT_AVAILABLE("ERROR_TICKET_400", 400),
    TICKET_SALE_NOT_FOUND("ERROR_TICKET_500", 400),
    TICKET_EVENT_NOT_FOUND("ERROR_TICKET_600", 400),
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
