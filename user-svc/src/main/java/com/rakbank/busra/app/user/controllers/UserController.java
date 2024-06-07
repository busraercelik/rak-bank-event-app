package com.rakbank.busra.app.user.controllers;

import com.rakbank.busra.app.user.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.user.dtos.UserDTO;
import com.rakbank.busra.app.user.models.User;
import com.rakbank.busra.app.user.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/user")
class UserController {

  private final UserService userService;

  @PostMapping
  BaseAPIResponse<User> create(UserDTO dto) {
    var result = userService.create(dto);
    return new BaseAPIResponse<>("200","user created successfully", result);
  }

  @GetMapping(path = "/{id}")
  BaseAPIResponse<User> fetch(@PathVariable("id") Long id) {
    var result = userService.fetch(id);
    return new BaseAPIResponse<>("200","user fetched successfully", result);
  }
}