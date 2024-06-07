package com.rakbank.busra.app.eventmgmt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.common.errors.BaseErrorCode;
import com.rakbank.busra.app.eventmgmt.common.exceptions.ApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class ResponseCodeInterceptor implements ClientHttpRequestInterceptor {
    private final ObjectMapper objectMapper;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        var response = execution.execute(request, body);
        var responseInputStream = response.getBody();

        byte[] responseByteArray = responseInputStream.readAllBytes();
        var apiResponse = objectMapper.readValue(responseByteArray, BaseAPIResponse.class);

        if(!("200".equals(apiResponse.getCode()))){
            throw new ApplicationException(apiResponse.getDetail(), getErrorCode(apiResponse, response));
        }
        return new ClientHttpResponseWrapper(response, responseByteArray);
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

    public static class ClientHttpResponseWrapper implements ClientHttpResponse {

        private final ClientHttpResponse originalResponse;
        private final byte[] responseBody;

        public ClientHttpResponseWrapper(ClientHttpResponse originalResponse, byte[] responseBody) {
            this.originalResponse = originalResponse;
            this.responseBody = responseBody;
        }

        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return originalResponse.getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return originalResponse.getStatusText();
        }

        @Override
        public void close() {
            originalResponse.close();
        }

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(responseBody);
        }

        @Override
        public HttpHeaders getHeaders() {
            return originalResponse.getHeaders();
        }
    }

}