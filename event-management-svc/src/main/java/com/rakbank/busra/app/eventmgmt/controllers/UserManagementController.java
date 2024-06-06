package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.dtos.UserDTO;
import com.rakbank.busra.app.eventmgmt.services.UserBusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/user")
class UserManagementController {

  private final UserBusinessService userBusinessService;

  @PostMapping
  UserDTO create(UserDTO dto) {
    return userBusinessService.createUser(dto);
  }

  @GetMapping
  List<UserDTO> fetch(@RequestParam("id") String id) {
    return List.of();
  }

}