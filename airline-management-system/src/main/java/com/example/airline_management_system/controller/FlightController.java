package com.example.airline_management_system.controller;

import com.example.airline_management_system.entity.Flight;
import com.example.airline_management_system.entity.FlightSchedule;
import com.example.airline_management_system.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights(@RequestParam(defaultValue = "asc") String sort) {
        return flightService.getAllFlights(sort);
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @GetMapping("/{id}/schedules")
    public List<FlightSchedule> getFlightSchedules(
            @PathVariable Long id,
            @RequestParam(required = false) List<LocalDate> dates) {
        return flightService.getFlightSchedules(id, dates);
    }
}
