package com.rakbank.busra.app.ticket.common.exceptions;


import com.rakbank.busra.app.ticket.common.errors.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    final String detail;
    final BaseErrorCode errorCode;
}
