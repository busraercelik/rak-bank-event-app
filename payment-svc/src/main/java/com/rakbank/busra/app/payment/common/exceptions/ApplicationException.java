package com.rakbank.busra.app.payment.common.exceptions;


import com.rakbank.busra.app.payment.common.errors.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    final String detail;
    final BaseErrorCode errorCode;
}
