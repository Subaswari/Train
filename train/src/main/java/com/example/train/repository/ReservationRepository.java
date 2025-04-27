package com.example.train.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.train.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long>{

	 List<Reservation> findByTrain_IdAndDate(Long trainId, LocalDate date);

	    List<Reservation> findByUser_Id(Long userId);
}
