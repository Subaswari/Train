package com.example.train.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.train.model.Trains;
import com.example.train.repository.TrainsRepository;

@Service
public class TrainsService {
	@Autowired
    private TrainsRepository trainRepository;

    // Save a train (Admin use)
    public String addTrain(Trains train) {
        trainRepository.save(train);
        return "Train added successfully!";
    }

    // Get all trains
    public List<Trains> getAllTrains() {
    	
        return trainRepository.findAll();
    }

    // Search trains
    public List<Trains> searchTrains(String source, String destination, LocalDate travelDate) {
        return trainRepository.findBySourceAndDestinationAndTravelDate(source, destination, travelDate);
    }

	public Trains findByTrainNumber(String trainNumber) {
		// TODO Auto-generated method stub
		return trainRepository.findByTrainNumber(trainNumber).orElse(null);
	}

	public void updateTrain(Trains train, Trains updatedTrain) {
		// TODO Auto-generated method stub
		train.setTrainName(updatedTrain.getTrainName());
		train.setSource(updatedTrain.getSource());
		train.setDestination(updatedTrain.getDestination());
		train.setDepartureTime(updatedTrain.getDepartureTime());
		train.setArrivalTime(updatedTrain.getArrivalTime());
		train.setTravelDate(updatedTrain.getTravelDate());
		train.setSeatsAvailable(updatedTrain.getSeatsAvailable());
		trainRepository.save(train);
	}

	public void deleteByTrainNumber(Trains train2) {
		// TODO Auto-generated method stub				
			trainRepository.delete(train2);				
	}
}
