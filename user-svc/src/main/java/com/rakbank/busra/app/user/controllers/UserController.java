package com.rakbank.busra.app.user.controllers;

import com.rakbank.busra.app.user.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/user")
class UserController {

  @PostMapping
  UserDTO create(UserDTO dto) {
    log.info("creating user : {}", dto);
    return dto;
  }

  @GetMapping
  List<UserDTO> fetch(@RequestParam("id") String id) {
    return List.of();
  }

}