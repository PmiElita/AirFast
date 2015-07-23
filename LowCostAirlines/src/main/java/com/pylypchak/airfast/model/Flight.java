package com.pylypchak.airfast.model;


import java.sql.Timestamp;
import java.util.Date;

import com.pylypchak.airfast.annotation.Column;

public class Flight {
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "admin_id")
	private Integer adminId;
	
	@Column(name = "from_airport_id")
	private Integer fromAirportId;
	
	@Column(name = "to_airport_id")
	private Integer toAirportId;
	
	@Column(name = "min_price")
	private Double minPrice;
	
	@Column(name = "max_price")
	private Double maxPrice;
	
	@Column(name = "hot_price")
	private Double hotPrice;
	
	@Column(name = "is_hot")
	private Boolean isHot;
	
	@Column(name = "seats")
	private Integer seats;
	
	@Column(name = "free_seats")
	private Integer freeSeats;
	
	@Column(name = "date_from")
	private Timestamp dateFrom;
	
	@Column(name = "date_to")
	private Timestamp dateTo;

	@Column(name="prime_price")
	private Double primePrice;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getFromAirportId() {
		return fromAirportId;
	}

	public void setFromAirportId(Integer fromAirportId) {
		this.fromAirportId = fromAirportId;
	}

	public Integer getToAirportId() {
		return toAirportId;
	}

	public void setToAirportId(Integer toAirportId) {
		this.toAirportId = toAirportId;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getHotPrice() {
		return hotPrice;
	}

	public void setHotPrice(Double hotPrice) {
		this.hotPrice = hotPrice;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
		freeSeats = seats;
	}

	public Integer getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(Integer freeSeats) {
		this.freeSeats = freeSeats;
	}

	public Timestamp getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Timestamp getDateTo() {
		return dateTo;
	}

	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}

	public Double getPrimePrice() {
		return primePrice;
	}

	public void setPrimePrice(Double primePrice) {
		this.primePrice = primePrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
 public double getActualPrice(){
	 if (isHot&&(hotPrice!=null)){
		 return hotPrice;
	 }
	double result =minPrice+(maxPrice-minPrice)/100*(100-(dateFrom.getTime()-new Date().getTime())/86400000);
	result=(double)Math.round(result);
	return result;
 }

}
