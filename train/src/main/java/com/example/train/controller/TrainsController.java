package com.example.train.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import com.example.train.model.Trains;
import com.example.train.service.TrainsService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/train")
public class TrainsController {
	
	@Autowired
    private TrainsService trainService;
	
    @PostMapping("/add")
    public ResponseEntity<String> addTrain(@RequestBody Trains train) {
    	try {
    		System.out.println(train);
    		if(StringUtils.isBlank(train.getTrainNumber())||StringUtils.isBlank(train.getTrainName())||StringUtils.isBlank(train.getSource())||StringUtils.isBlank(train.getDestination())||StringUtils.isBlank(train.getDepartureTime())||StringUtils.isBlank(train.getArrivalTime())||train.getSeatsAvailable()<=0||train.getTravelDate()==null||train.getSource().equalsIgnoreCase(train.getDestination())) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fill all Mandatory fields properly");	
    		}
    		trainService.addTrain(train);
    		return ResponseEntity.status(HttpStatus.CREATED).body("Train added successfully!");
    	}catch(Exception e){
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add trains.");
    	}   
    }
    @GetMapping("/all")
    public List<Trains> getAllTrains() {
        return trainService.getAllTrains();
    }
    @GetMapping("/search")
    public List<Trains> searchTrains(
    		@RequestParam(required=false) String source,
    		@RequestParam(required=false) String destination,
    		@RequestParam(required=false) String date){
    	if(source==null || source.trim().isEmpty()) {
    		throw new IllegalArgumentException("Source must not be empty");
    	}
    	if(destination==null || destination.trim().isEmpty()) {
    		throw new IllegalArgumentException("Destination must not be empty");
    	}
    	if(date==null || date.trim().isEmpty()) {
    		throw new IllegalArgumentException("Date must not be empty");
    	}
    	if(source.equalsIgnoreCase(destination)) {
    		throw new IllegalArgumentException("Source and Destination must not be equal");
    	}
    	LocalDate travelDate;
    	try {
    		 travelDate= LocalDate.parse(date);
    	}catch(DateTimeParseException e) {
    		throw new IllegalArgumentException("Invalid date format");
    	}    	
    		return trainService.searchTrains(source, destination, travelDate);    	 	
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> updateTrain(@RequestBody Trains updatedTrain){
    	try {
    		if(StringUtils.isBlank(updatedTrain.getTrainNumber())) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fill Train Number");	   		
    		}
    		Trains train=trainService.findByTrainNumber(updatedTrain.getTrainNumber());
    		if(train==null) {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train not found");
    		}
    		if(StringUtils.isBlank(updatedTrain.getTrainNumber())||StringUtils.isBlank(updatedTrain.getTrainName())||StringUtils.isBlank(updatedTrain.getSource())||StringUtils.isBlank(updatedTrain.getDestination())||StringUtils.isBlank(updatedTrain.getDepartureTime())||StringUtils.isBlank(updatedTrain.getArrivalTime())||updatedTrain.getSeatsAvailable()<=0||updatedTrain.getTravelDate()==null||updatedTrain.getSource().equalsIgnoreCase(updatedTrain.getDestination())) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fill all Mandatory fields properly");	
    		}
    		trainService.updateTrain(train,updatedTrain);
    		return ResponseEntity.ok("Train updated successfully!");
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update train");
    	}
    }
    
    @PostMapping("/delete")
    public ResponseEntity<String> deleteTrain(@RequestBody Trains trainData){
    	try {
    		if(StringUtils.isBlank(trainData.getTrainNumber())) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter Train number");	
    		
    		}
    		Trains train=trainService.findByTrainNumber(trainData.getTrainNumber());
    		if(train==null) {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train not found");
    		}
    		trainService.deleteByTrainNumber(train);
    		return ResponseEntity.ok("Train deleted successfully");
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete booked train.");
    	}
    }
}

//http://localhost:8082/api/train/add
//http://localhost:8082/api/train/all
//http://localhost:8082/api/train/search
//http://localhost:8082/api/train/update
//http://localhost:8082/api/train/delete