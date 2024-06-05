package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.dtos.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/user")
class UserManagementController {

  @PostMapping
  UserDTO create(UserDTO dto) {
    return dto;
  }

  @GetMapping
  List<UserDTO> fetch(@RequestParam("id") String id) {
    return List.of();
  }

}