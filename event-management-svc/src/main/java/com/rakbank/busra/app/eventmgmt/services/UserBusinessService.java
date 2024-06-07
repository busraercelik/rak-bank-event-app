package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.UserClient;
import com.rakbank.busra.app.eventmgmt.dtos.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessService {

    private final UserClient userClient;


    public UserDTO createUser(UserDTO dto) {
        return userClient.createUser(dto);
    }

    public UserDTO getUser(String id) {
        return userClient.getById(id);
    }

}
