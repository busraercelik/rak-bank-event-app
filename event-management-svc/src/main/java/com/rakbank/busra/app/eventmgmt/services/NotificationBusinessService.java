package com.rakbank.busra.app.eventmgmt.services;

import com.rakbank.busra.app.eventmgmt.clients.NotificationClient;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.dtos.requests.NotificationRequestDTO;
import com.rakbank.busra.app.eventmgmt.dtos.responses.NotificationResponseDTO;
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
