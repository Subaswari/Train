package com.example.train.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.train.model.Reservation;
import com.example.train.service.ReservationService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/reservations")
public class ReservationController {
	
	@Autowired
    private ReservationService reservationService;
	
    // 1. Get booked seats for a train and date
    @PostMapping("/availability")
    public ResponseEntity<List<Integer>> getBookedSeats(@RequestBody Map<String, String> request) {
    	try {
    		if(StringUtils.isBlank(request.get("id"))||StringUtils.isBlank(request.get("travelDate"))){
    			return (ResponseEntity<List<Integer>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    		Long trainId = Long.parseLong(request.get("id"));
            LocalDate date = LocalDate.parse(request.get("travelDate"));
            List<Integer> bookedSeats = reservationService.getBookedSeats(trainId, date);
            return ResponseEntity.ok(bookedSeats);
    		
    	}catch(Exception e) {
    		return (ResponseEntity<List<Integer>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    	}       
    }
    
    // 2. Book a ticket
    @PostMapping("/book")
    public ResponseEntity<Reservation> bookTicket(@RequestBody Reservation reservation) {
    	try {
    		if(StringUtils.isBlank(reservation.getUser().getEmail())||reservation.getTrain().getId()==null) {
    			return (ResponseEntity<Reservation>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    		Reservation booked = reservationService.bookTicket(reservation);
            return ResponseEntity.ok(booked);
    	}catch(Exception e) {
    		return (ResponseEntity<Reservation>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    // 3. Get user reservation history
    @PostMapping("/user/reservations")
    public ResponseEntity<List<Reservation>> getUserReservations(@RequestBody Map<String, String> request){
    	try {
    		if(StringUtils.isEmpty(request.get("userId"))) {
    			return (ResponseEntity<List<Reservation>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    		 Long userId = Long.parseLong(request.get("userId"));        
    	        List<Reservation> history = reservationService.getUserReservations(userId);
    	        return ResponseEntity.ok(history);
    	}catch(Exception e) {
    		return (ResponseEntity<List<Reservation>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
       
    }
}

//http://localhost:8082/api/reservations/availability
//http://localhost:8082/api/reservations/book
//http://localhost:8082/api/reservations/user/reservations

