package com.rakbank.busra.app.user.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseAPIResponse<T> {
    String code;
    String detail;
    T result;
}
