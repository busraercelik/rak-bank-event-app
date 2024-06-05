package com.rakbank.busra.app.eventmgmt.clients;

import com.rakbank.busra.app.eventmgmt.config.AppConfig;
import com.rakbank.busra.app.eventmgmt.dtos.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@AllArgsConstructor
public class UserClient {

    private final AppConfig appConfig;
    private final RestClient restClient;

    public UserDTO createUser(UserDTO request){
        ResponseEntity<UserDTO> response = restClient.post()
                .uri(appConfig.getUserServiceBaseUrl()+"/v1/user")
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(UserDTO.class);

        return response.getBody();
    }

}


