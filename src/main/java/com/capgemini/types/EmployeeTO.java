package com.capgemini.types;

import java.util.Objects;
import java.util.stream.Stream;

import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.domain.PositionEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;

public class EmployeeTO {
	
	private Long id;
	private PersonalDataTO personalData;
	private AddressTO address;
	private AgencyTO agency;
	private PositionTO position;

	public EmployeeTO() {}

	public EmployeeTO(Builder builder) {
		this.id = builder.id;
		this.personalData = builder.personalData;
		this.address = builder.address;
		this.agency = builder.agency;
		this.position = builder.position;
	}
	
	public PersonalDataTO getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalDataTO personalData) {
		this.personalData = personalData;
	}

	public AgencyTO getAgency() {
		return agency;
	}

	public void setAgency(AgencyTO agency) {
		this.agency = agency;
	}

	public PositionTO getPosition() {
		return position;
	}

	public void setPosition(PositionTO position) {
		this.position = position;
	}

	public Long getId() {
		return id;
	}
	
	public AddressTO getAddress() {
		return address;
	}

	public void setAddress(AddressTO address) {
		this.address = address;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder {
		private long id;
		private PersonalDataTO personalData;
		private AddressTO address;
		private AgencyTO agency;
		private PositionTO position;
		
		public Builder withId(long id){
			this.id = id;
			return this;
		}
		
		public Builder withPersonalData(PersonalDataTO personalData){
			this.personalData = personalData;
			return this;
		}
		
		public Builder withAddress(AddressTO addressTO){
			this.address = addressTO;
			return this;
		}
		
		public Builder withAgency(AgencyTO agency){
			this.agency = agency;
			return this;
		}
		
		public Builder withPosition(PositionTO position){
			this.position = position;
			return this;
		}
		
		public EmployeeTO build() throws MandatoryValueNotFilledException {
			if(isFilled()){
			return new EmployeeTO(this);
			}
			throw new MandatoryValueNotFilledException();
			
		}
		
		private boolean isFilled(){
			return Stream.of(personalData,agency,position)
			      .noneMatch(Objects::isNull);
		}
		
	}
	
}
