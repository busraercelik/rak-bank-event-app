package com.rakbank.busra.app.eventmgmt.clients.userservice;

import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import com.rakbank.busra.app.eventmgmt.dtos.requests.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@AllArgsConstructor
public class UserClient {

    private final AppConfig appConfig;
    private final RestClient restClient;

    public BaseAPIResponse<UserDTO> createUser(UserDTO request) {
        var response = restClient.post()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/user")
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<UserDTO>>() {});

        return response.getBody();
    }


    public BaseAPIResponse<UserDTO> getById(String id) {
        return restClient.get()
                .uri(appConfig.getUserServiceBaseUrl() + "/v1/user/{id}", id)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<BaseAPIResponse<UserDTO>>() {})
                .getBody();
    }
}


