package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.notificationservice.NotificationClient;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.requests.NotificationRequestDTO;
import com.rakbank.busra.app.eventmgmt.clients.notificationservice.dtos.commons.responses.NotificationResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationBusinessService {

    private NotificationClient notificationClient;

    public BaseAPIResponse<NotificationResponseDTO> notifyUser(NotificationRequestDTO request) {
        return notificationClient.notifyUser(request);
    }
}
