package com.rakbank.busra.app.eventmgmt.dtos.requests;

import lombok.Data;

@Data
public class CancelTicketBusinessRequest {
    Long userId;
    String referenceId;
}
