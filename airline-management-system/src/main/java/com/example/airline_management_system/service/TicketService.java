package com.example.airline_management_system.service;

import com.example.airline_management_system.dto.TicketRequest;
import com.example.airline_management_system.entity.FlightSchedule;
import com.example.airline_management_system.entity.Ticket;
import com.example.airline_management_system.exception.ResourceNotFoundException;
import com.example.airline_management_system.repository.FlightScheduleRepository;
import com.example.airline_management_system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    @Transactional
    public Ticket createTicket(TicketRequest ticketRequest) {
        FlightSchedule schedule = flightScheduleRepository.findById(ticketRequest.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight schedule not found with id: " + ticketRequest.getScheduleId()));
        if (schedule.getAvailableSeats() <= 0) {
            throw new IllegalStateException("No available seats for this schedule");
        }

        Ticket ticket = new Ticket();
        ticket.setFlightSchedule(schedule);
        ticket.setPassengerName(ticketRequest.getPassengerName());
        ticketRepository.save(ticket);

        schedule.setAvailableSeats(schedule.getAvailableSeats() - 1);
        flightScheduleRepository.save(schedule);

        return ticket;
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
    }

    @Transactional
    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
        FlightSchedule schedule = ticket.getFlightSchedule();
        ticketRepository.delete(ticket);
        schedule.setAvailableSeats(schedule.getAvailableSeats() + 1);
        flightScheduleRepository.save(schedule);
    }
}
