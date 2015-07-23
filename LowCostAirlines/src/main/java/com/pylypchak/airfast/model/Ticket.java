package com.pylypchak.airfast.model;

import java.sql.Timestamp;

import com.pylypchak.airfast.annotation.Column;

public class Ticket {

	@Column(name = "id")
	private Integer id;

	@Column(name = "flight_id")
	private Integer flightId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "price")
	private Double price;

	@Column(name = "status")
	private String status;

	@Column(name = "date")
	private Timestamp date;

	@Column(name = "bagage_quantity")
	private Integer bagageQuantity;

	@Column(name = "is_prime_registration")
	private Boolean isPrimeRegistration;

	@Column(name = "is_prime_boarding")
	private Boolean isPrimeBoarding;

	public Ticket(){
		isPrimeBoarding=false;
		isPrimeRegistration=false;
		bagageQuantity=0;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getBagageQuantity() {
		return bagageQuantity;
	}

	public void setBagageQuantity(Integer bagageQuantity) {
		this.bagageQuantity = bagageQuantity;
	}

	public Boolean getIsPrimeRegistration() {
		return isPrimeRegistration;
	}

	public void setIsPrimeRegistration(Boolean isPrimeRegistration) {
		this.isPrimeRegistration = isPrimeRegistration;
	}

	public Boolean getIsPrimeBoarding() {
		return isPrimeBoarding;
	}

	public void setIsPrimeBoarding(Boolean isPrimeBoarding) {
		this.isPrimeBoarding = isPrimeBoarding;
	}

}
