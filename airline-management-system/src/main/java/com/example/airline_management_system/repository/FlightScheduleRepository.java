package com.example.airline_management_system.repository;

import com.example.airline_management_system.entity.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
    List<FlightSchedule> findByFlightFlightId(Long flightId);
    List<FlightSchedule> findByFlightFlightIdAndDateIn(Long flightId, List<LocalDate> dates);
}
