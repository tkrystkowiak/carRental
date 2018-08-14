package com.capgemini.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.capgemini.domain.RentalEntity.RentalBuilder;

@Entity
@Table(name = "CARS")
public class CarEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 50)
    private String type;
	
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
	@JoinTable(name = "car_employee", joinColumns = { @JoinColumn(name = "car_id") },inverseJoinColumns = { @JoinColumn(name = "employee_id") })
	private List<EmployeeEntity> listOfGuardians;

	public CarEntity() {
	}

	public CarEntity(CarBuilder builder) {
		this.type = builder.type;
		this.brand = builder.brand;
		this.engineCapacity = builder.engineCapacity;
		this.mileage = builder.mileage;
		this.color = builder.color;
		this.horsePower = builder.horsePower;
		this.yearOfProduction = builder.yearOfProduction;
		this.listOfGuardians = builder.listOfGuardians;
	}
	
	public Long getId() {
		return id;
	}

	public String getCarType() {
		return type;
	}

	public void setCarType(String type) {
		this.type = type;
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
	
	public List<EmployeeEntity> getListOfGuardians() {
		return listOfGuardians;
	}

	public void setListOfGuardians(List<EmployeeEntity> listOfGuardians) {
		this.listOfGuardians = listOfGuardians;
	}
	
	public static CarBuilder newBuilder() {
        return new CarBuilder();
    }
	
	public static class CarBuilder {
		
	    private String type;
	    private String brand;
	    private int engineCapacity;
	    private int mileage;
		private String color;
		private int horsePower;
		private int yearOfProduction;
		private List<EmployeeEntity> listOfGuardians;
		
		
		public CarBuilder withType(String type) {
			this.type = type;
			return this;
        }
		
		public CarBuilder withBrand(String brand) {
			this.brand = brand;
			return this;
        }
		
		public CarBuilder withEngineCapacity(int engineCapacity) {
			this.engineCapacity = engineCapacity;
			return this;
        }
		
		public CarBuilder withMileage(int mileage) {
			this.mileage = mileage;
			return this;
        }
		
		public CarBuilder withColor(String color) {
			this.color = color;
			return this;
        }
		
		public CarBuilder withHorsePower(int horsePower) {
			this.horsePower = horsePower;
			return this;
        }
		
		public CarBuilder withYearOfProduction(int yearOfProduction) {
			this.yearOfProduction = yearOfProduction;
			return this;
        }
		
		public CarBuilder withGuardiansList(List<EmployeeEntity> listOfGuardians){
			this.listOfGuardians = listOfGuardians;
			return this;
		}
		

		public CarEntity build() throws MandatoryValueNotFilledException {
			if(isFilled()){
			return new CarEntity(this);
			}
			throw new MandatoryValueNotFilledException();
			
		}
		
		private boolean isFilled(){
			return Stream.of(type,brand,engineCapacity,mileage,color,horsePower,yearOfProduction)
			      .noneMatch(Objects::isNull);
			}
		
	}
	
}
