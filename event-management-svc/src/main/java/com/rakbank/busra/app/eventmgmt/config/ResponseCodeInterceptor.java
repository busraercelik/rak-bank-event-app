package com.rakbank.busra.app.eventmgmt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.common.errors.BaseErrorCode;
import com.rakbank.busra.app.eventmgmt.common.exceptions.ApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class ResponseCodeInterceptor implements ClientHttpRequestInterceptor {
    private final ObjectMapper objectMapper;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);
        var apiResponse = objectMapper.readValue(response.getBody(), BaseAPIResponse.class);
        if(!("200".equals(apiResponse.getCode()))){
            throw new ApplicationException(apiResponse.getDetail(), getErrorCode(apiResponse, response));
        }
        return response;
    }

    private static BaseErrorCode getErrorCode(BaseAPIResponse<?> apiResponse, ClientHttpResponse response) {
        return new BaseErrorCode() {
            @Override
            public String getCode() {
                return apiResponse.getCode();
            }

            @Override
            public int getHttpStatus() {
                try {
                    return response.getStatusCode().value();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}