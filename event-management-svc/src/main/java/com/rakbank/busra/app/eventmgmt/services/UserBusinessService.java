package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.userservice.UserClient;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.clients.userservice.dtos.commons.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserBusinessService {

    private final UserClient userClient;

    public UserDTO createUser(UserDTO dto) {
        var user = userClient.createUser(dto).getResult();
        log.info("User created {}", user);
        return user;
    }

    public BaseAPIResponse<UserDTO> getUser(Long id) {
        return userClient.getById(id);
    }

}
