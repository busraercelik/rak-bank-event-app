package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.userservice.UserClient;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.clients.userservice.dtos.commons.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessService {

    private final UserClient userClient;

    public BaseAPIResponse<UserDTO> createUser(UserDTO dto) {
        return userClient.createUser(dto);
    }

    public BaseAPIResponse<UserDTO> getUser(Long id) {
        return userClient.getById(id);
    }

}
