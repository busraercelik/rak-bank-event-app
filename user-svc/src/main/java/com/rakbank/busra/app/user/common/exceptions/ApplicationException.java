package com.rakbank.busra.app.user.common.exceptions;


import com.rakbank.busra.app.user.common.errors.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    final String detail;
    final BaseErrorCode errorCode;
}
