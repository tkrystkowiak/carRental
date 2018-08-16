package com.capgemini.types;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.MandatoryValueNotFilledException;

public class CarTO {
	
    private Long id;
    private String type;
    private String brand;
    private int engineCapacity;
    private int mileage;
	private String color;
	private int horsePower;
	private int yearOfProduction;
	private List<EmployeeTO> listOfGuardians;

	public CarTO(Builder builder) {
		this.id = builder.id;
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
	
	public List<EmployeeTO> getListOfGuardians() {
		return listOfGuardians;
	}

	public void setListOfGuardians(List<EmployeeTO> listOfGuardians) {
		this.listOfGuardians = listOfGuardians;
	}
	
	public static Builder newBuilder() {
        return new Builder();
    }
	
	public static class Builder {
		
		private long id;
	    private String type;
	    private String brand;
	    private int engineCapacity;
	    private int mileage;
		private String color;
		private int horsePower;
		private int yearOfProduction;
		private List<EmployeeTO> listOfGuardians;
		
		
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
		
		public Builder withGuardiansList(List<EmployeeTO> list){
			this.listOfGuardians = list;
			return this;
		}
		

		public CarTO build() throws MandatoryValueNotFilledException {
			if(isFilled()){
			return new CarTO(this);
			}
			throw new MandatoryValueNotFilledException();
			
		}
		
		private boolean isFilled(){
			return Stream.of(type,brand,engineCapacity,mileage,color,horsePower,yearOfProduction)
			      .noneMatch(Objects::isNull);
		}
		
	}
	
}
