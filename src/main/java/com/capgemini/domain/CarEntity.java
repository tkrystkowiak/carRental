package com.capgemini.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CARS")
public class CarEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 50)
    private String carType;
	
	@Column(nullable = false, length = 50)
    private String brand;
	
	@Column(nullable = false)
    private int engineCapacity;
	
	@Column(nullable = false)
    private int mileage;
	
	@Column(nullable = false, length = 50)
	private String color;
	
	@Column(nullable = false)
	private int horsePower;
	
	@Column(nullable = false)
	private int yearOfProduction;
	
	@ManyToMany
	@JoinTable(name = "car_employee", joinColumns = { @JoinColumn(name = "car_id") },inverseJoinColumns = { @JoinColumn(name = "employee_id") }
          )
private List<EmployeeEntity> listOfCarers;

	public CarEntity() {
	}

	public CarEntity(String carType, String brand, int engineCapacity, int mileage, String color, int horsePower,
			int yearOfProduction) {
		this.carType = carType;
		this.brand = brand;
		this.engineCapacity = engineCapacity;
		this.mileage = mileage;
		this.color = color;
		this.horsePower = horsePower;
		this.yearOfProduction = yearOfProduction;
	}
	
	public Long getId() {
		return id;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
	
}
