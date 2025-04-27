package com.example.train.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.train.model.Trains;

@Repository
public interface TrainsRepository extends JpaRepository<Trains,Long>{

	List<Trains> findBySourceAndDestinationAndTravelDate(String source, String destination, LocalDate travelDate);

	Optional<Trains> findByTrainNumber(String trainNumber);

	
}
