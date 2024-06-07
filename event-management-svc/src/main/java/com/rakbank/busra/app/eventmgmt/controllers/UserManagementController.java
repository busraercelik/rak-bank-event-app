package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.clients.userservice.dtos.commons.UserDTO;
import com.rakbank.busra.app.eventmgmt.services.UserBusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/user")
class UserManagementController {

    private final UserBusinessService userBusinessService;

    @PostMapping
    BaseAPIResponse<UserDTO> create(UserDTO dto) {
        return userBusinessService.createUser(dto);
    }

    @GetMapping
    BaseAPIResponse<UserDTO> fetch(@RequestParam("id") String id) {
        return userBusinessService.getUser(id);
    }

}