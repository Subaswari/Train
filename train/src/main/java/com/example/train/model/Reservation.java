package com.example.train.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Reservation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
    private User user;
	
	@ManyToOne
	@JoinColumn(name="train_id",nullable=false)
    private Trains train;
	
    private int seatNumber;
    private LocalDate date;
    private String status="CONFIRMED";
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Trains getTrain() {
		return train;
	}
	public void setTrain(Trains train) {
		this.train = train;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
