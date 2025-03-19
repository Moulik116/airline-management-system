package com.example.airline_management_system.service;

import com.example.airline_management_system.entity.Flight;
import com.example.airline_management_system.entity.FlightSchedule;
import com.example.airline_management_system.exception.ResourceNotFoundException;
import com.example.airline_management_system.repository.FlightRepository;
import com.example.airline_management_system.repository.FlightScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    public List<Flight> getAllFlights(String sort) {
        if ("asc".equalsIgnoreCase(sort)) {
            return flightRepository.findAll(Sort.by(Sort.Direction.ASC, "flightNumber"));
        } else {
            return flightRepository.findAll(Sort.by(Sort.Direction.DESC, "flightNumber"));
        }
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
    }

    public List<FlightSchedule> getFlightSchedules(Long flightId, List<LocalDate> dates) {
        if (dates != null && !dates.isEmpty()) {
            return flightScheduleRepository.findByFlightFlightIdAndDateIn(flightId, dates);
        } else {
            return flightScheduleRepository.findByFlightFlightId(flightId);
        }
    }
}
