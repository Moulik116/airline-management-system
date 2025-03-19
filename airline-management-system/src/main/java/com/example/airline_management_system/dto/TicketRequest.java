package com.example.airline_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest {
    @NotNull(message = "Schedule ID cannot be null")
    private Long scheduleId;

    @NotBlank(message = "Passenger name cannot be blank")
    private String passengerName;
}
