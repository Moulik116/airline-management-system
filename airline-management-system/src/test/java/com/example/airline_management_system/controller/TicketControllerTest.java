package com.example.airline_management_system.controller;

import com.example.airline_management_system.dto.TicketRequest;
import com.example.airline_management_system.entity.FlightSchedule;
import com.example.airline_management_system.entity.Ticket;
import com.example.airline_management_system.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateTicket() throws Exception {
        TicketRequest request = new TicketRequest();
        request.setPassengerName("John Doe");
        request.setScheduleId(101L);

        FlightSchedule schedule = new FlightSchedule();
        schedule.setScheduleId(101L);

        Ticket ticket = new Ticket();
        ticket.setPassengerName("John Doe");
        ticket.setFlightSchedule(schedule);

        Mockito.when(ticketService.createTicket(any(TicketRequest.class))).thenReturn(ticket);

        mockMvc.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.passengerName").value("John Doe"));
    }

    @Test
    public void testGetTicketById() throws Exception {
        FlightSchedule schedule = new FlightSchedule();
        schedule.setScheduleId(202L);

        Ticket ticket = new Ticket();
        ticket.setPassengerName("Alice");
        ticket.setFlightSchedule(schedule);

        Mockito.when(ticketService.getTicketById(1L)).thenReturn(ticket);

        mockMvc.perform(get("/tickets/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.passengerName").value("Alice"));
    }

    @Test
    public void testDeleteTicket() throws Exception {
        Mockito.doNothing().when(ticketService).deleteTicket(1L);

        mockMvc.perform(delete("/tickets/1"))
                .andExpect(status().isOk());
    }
}
