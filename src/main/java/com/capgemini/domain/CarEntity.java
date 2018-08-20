package com.capgemini.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.capgemini.domain.RentalEntity.Builder;

@Entity
@Table(name = "CARS")
public class CarEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "car_employee", joinColumns = { @JoinColumn(name = "car_id") },inverseJoinColumns = { @JoinColumn(name = "employee_id") })
	private List<EmployeeEntity> listOfGuardians;

	public CarEntity() {
	}

	public CarEntity(Builder builder) {
		if(builder.id!=0){
			this.id = builder.id;
		}
		this.type = builder.type;
		this.brand = builder.brand;
		this.engineCapacity = builder.engineCapacity;
		this.mileage = builder.mileage;
		this.color = builder.color;
		this.horsePower = builder.horsePower;
		this.yearOfProduction = builder.yearOfProduction;
		this.listOfGuardians = builder.listOfGuardians;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
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
	
	public static Builder newBuilder() {
        return new Builder();
    }
	
	public static class Builder {
		
		private long id;
	    private String type;
	    private String brand;
	    private Integer engineCapacity;
	    private Integer mileage;
		private String color;
		private Integer horsePower;
		private Integer yearOfProduction;
		private List<EmployeeEntity> listOfGuardians;
		
		public Builder withId(long id){
			this.id = id;
			return this;
		}
		
		public Builder withType(String type) {
			this.type = type;
			return this;
        }
		
		public Builder withBrand(String brand) {
			this.brand = brand;
			return this;
        }
		
		public Builder withEngineCapacity(int engineCapacity) {
			this.engineCapacity = engineCapacity;
			return this;
        }
		
		public Builder withMileage(int mileage) {
			this.mileage = mileage;
			return this;
        }
		
		public Builder withColor(String color) {
			this.color = color;
			return this;
        }
		
		public Builder withHorsePower(int horsePower) {
			this.horsePower = horsePower;
			return this;
        }
		
		public Builder withYearOfProduction(int yearOfProduction) {
			this.yearOfProduction = yearOfProduction;
			return this;
        }
		
		public Builder withGuardiansList(List<EmployeeEntity> listOfGuardians){
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
