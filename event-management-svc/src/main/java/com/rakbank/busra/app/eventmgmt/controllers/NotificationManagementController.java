package com.rakbank.busra.app.eventmgmt.controllers;

import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.dtos.requests.NotificationRequestDTO;
import com.rakbank.busra.app.eventmgmt.dtos.responses.NotificationResponseDTO;
import com.rakbank.busra.app.eventmgmt.services.NotificationBusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/notify")
class NotificationManagementController {

  private final NotificationBusinessService notificationBusinessService;

  @PostMapping
  BaseAPIResponse<NotificationResponseDTO> notifyUser(NotificationRequestDTO request) {
    return notificationBusinessService.notifyUser(request);
  }

}