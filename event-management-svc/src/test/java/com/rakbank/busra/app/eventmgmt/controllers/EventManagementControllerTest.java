package com.rakbank.busra.app.eventmgmt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakbank.busra.app.eventmgmt.clients.eventservice.EventClient;
import com.rakbank.busra.app.eventmgmt.clients.eventservice.dtos.commons.EventDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.TicketClient;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.CreateTicketEventInventoryResultDTO;
import com.rakbank.busra.app.eventmgmt.clients.ticketservice.dtos.responses.EventTicketInventoryResponseDTO;
import com.rakbank.busra.app.eventmgmt.common.dto.BaseAPIResponse;
import com.rakbank.busra.app.eventmgmt.dtos.requests.EventCreateBusinessRequest;
import com.rakbank.busra.app.eventmgmt.services.EventBusinessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EventManagementControllerTest {

  @InjectMocks
  private EventBusinessService eventBusinessService;

  @MockBean
  private EventClient eventClient;

  @MockBean
  private TicketClient ticketClient;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;


  EventDTO createdEvent;
  EventDTO updatedEvent;
  CreateTicketEventInventoryResultDTO inventoryResultDTO;
  EventTicketInventoryResponseDTO eventTicketInventoryResponseDTO;
  EventCreateBusinessRequest eventCreateBusinessRequest;

  @BeforeEach
  void setUp() {
    createdEvent = new EventDTO();
    createdEvent.setId(100L);
    createdEvent.setName("Devoxx Dubai");
    createdEvent.setDescription("Join us at the first Devoxx Dubai event to learn about the latest trends in the Java world");
    createdEvent.setHost("Jose Paumard");

    eventTicketInventoryResponseDTO = new EventTicketInventoryResponseDTO();
    inventoryResultDTO = new CreateTicketEventInventoryResultDTO(true, eventTicketInventoryResponseDTO);
    eventTicketInventoryResponseDTO.setId(200L);
    eventTicketInventoryResponseDTO.setEventId(createdEvent.getId());

    updatedEvent = new EventDTO();
    updatedEvent.setId(100L);
    updatedEvent.setName("Devoxx Dubai");
    updatedEvent.setDescription("Join us at the first Devoxx Dubai event to learn about the latest trends in the Java world");
    updatedEvent.setHost("Jose Paumard");
    updatedEvent.setEventTicketInventoryId(eventTicketInventoryResponseDTO.getId());

    eventCreateBusinessRequest = new EventCreateBusinessRequest();
    eventCreateBusinessRequest.setName(createdEvent.getName());
    eventCreateBusinessRequest.setHost(createdEvent.getHost());
    eventCreateBusinessRequest.setDescription(createdEvent.getDescription());
  }

  @Test
  @DisplayName("POST /v1/event - CREATED")
  void testCreateEvent() throws Exception {
    // set up mocked responses from external services
    when(eventClient.createEvent(any())).thenReturn(getBaseAPIResponse(createdEvent));
    when(ticketClient.createEventTicketInventory(any())).thenReturn(getBaseAPIResponse(inventoryResultDTO));
    when(eventClient.updateWithInventoryId(any(), any())).thenReturn(getBaseAPIResponse(updatedEvent));

    // execute the post request
    Integer expectedEventId = Math.toIntExact(updatedEvent.getId());
    String expectedEventName = updatedEvent.getName();

    mockMvc.perform(post("/v1/event")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(eventCreateBusinessRequest)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.result.id", is(expectedEventId)))
        .andExpect(jsonPath("$.result.name", is(expectedEventName)));
  }

  private <T> BaseAPIResponse<T> getBaseAPIResponse(T result) {
    return new BaseAPIResponse<>("200", "", result);
  }

}