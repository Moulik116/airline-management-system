package com.example.airline_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "flightschedule")
@Getter
@Setter
public class FlightSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "flightId")
    private Flight flight;

    private LocalDate date;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int availableSeats;
    private int totalSeats;
}
