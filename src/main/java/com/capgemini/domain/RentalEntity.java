package com.capgemini.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RENTALS")
public class RentalEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cutomer_id")
	private CustomerEntity customer;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "car_id")
	private CarEntity car;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "rent_agency_id")
	private AgencyEntity rentAgencyId;
	
	@Column(name = "rent_date",nullable = false)
	private Date rentDate;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "return_agency_id")
	private AgencyEntity returnAgencyId;
	
	@Column(name = "return_date",nullable = false)
	private Date returnDate;
	
	@Column(nullable = false)
	private int charge;

	public RentalEntity() {
	}

	public RentalEntity(CustomerEntity customer, CarEntity car, AgencyEntity rentAgencyId, Date rentDate,
			AgencyEntity returnAgencyId, Date returnDate, int charge) {
		this.customer = customer;
		this.car = car;
		this.rentAgencyId = rentAgencyId;
		this.rentDate = rentDate;
		this.returnAgencyId = returnAgencyId;
		this.returnDate = returnDate;
		this.charge = charge;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public CarEntity getCar() {
		return car;
	}

	public void setCar(CarEntity car) {
		this.car = car;
	}

	public AgencyEntity getRentAgencyId() {
		return rentAgencyId;
	}

	public void setRentAgencyId(AgencyEntity rentAgencyId) {
		this.rentAgencyId = rentAgencyId;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public AgencyEntity getReturnAgencyId() {
		return returnAgencyId;
	}

	public void setReturnAgencyId(AgencyEntity returnAgencyId) {
		this.returnAgencyId = returnAgencyId;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public Long getId() {
		return id;
	}
	
}
