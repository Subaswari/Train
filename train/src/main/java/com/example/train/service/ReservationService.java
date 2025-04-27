package com.example.train.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.train.model.Reservation;
import com.example.train.model.Trains;
import com.example.train.model.User;
import com.example.train.repository.ReservationRepository;
import com.example.train.repository.TrainsRepository;
import com.example.train.repository.UserRepository;

@Service
public class ReservationService {
	
	@Autowired
    private ReservationRepository reservationRepository;
	
	@Autowired
	private TrainsRepository trainRepository;
	
	@Autowired
    private UserRepository userRepository;

	public List<Integer> getBookedSeats(Long trainId, LocalDate date) {
        return reservationRepository.findByTrain_IdAndDate(trainId, date)
                .stream()
                .map(Reservation::getSeatNumber)
                .collect(Collectors.toList());
    }

    public Reservation bookTicket(Reservation reservation) {
        String userMail = reservation.getUser().getEmail();
        Long trainId = reservation.getTrain().getId();

        User user = userRepository.findByEmail(userMail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Trains train = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found"));

        reservation.setUser(user);
        reservation.setTrain(train);
        reservation.setStatus("CONFIRMED");
        
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getUserReservations(Long userId) {
        return reservationRepository.findByUser_Id(userId);
    }
}
