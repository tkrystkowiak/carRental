package com.capgemini.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.domain.CarEntity.Builder;


@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Embedded
	private PersonalDataEmbedded personalData;

	@Embedded
	private AddressEmbedded address;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "agency_id")
	private AgencyEntity agency;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "position_id")
	private PositionEntity position;
	

	public EmployeeEntity() {}

	public EmployeeEntity(Builder builder) {
		this.personalData = builder.personalData;
		this.address = builder.address;
		this.agency = builder.agency;
		this.position = builder.position;
	}
	
	public PersonalDataEmbedded getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalDataEmbedded personalData) {
		this.personalData = personalData;
	}

	public AgencyEntity getAgency() {
		return agency;
	}

	public void setAgency(AgencyEntity agency) {
		this.agency = agency;
	}

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setAddress(AddressEmbedded address) {
		this.address = address;
	}
	
	public AddressEmbedded getAddress() {
		return address;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder {
		
		private long id;
		private PersonalDataEmbedded personalData;
		private AddressEmbedded address;
		private AgencyEntity agency;
		private PositionEntity position;
		
		public Builder withId(long id){
			this.id = id;
			return this;
		}
		
		public Builder withPersonalData(PersonalDataEmbedded personalData){
			this.personalData = personalData;
			return this;
		}
		
		public Builder withAddress(AddressEmbedded address){
			this.address = address;
			return this;
		}
		
		public Builder withAgency(AgencyEntity agency){
			this.agency = agency;
			return this;
		}
		
		public Builder withPosition(PositionEntity position){
			this.position = position;
			return this;
		}
		
		public EmployeeEntity build() throws MandatoryValueNotFilledException {
			if(isFilled()){
			return new EmployeeEntity(this);
			}
			throw new MandatoryValueNotFilledException();
		}
		
		private boolean isFilled(){
			return Stream.of(personalData,agency,position)
			      .noneMatch(Objects::isNull);
		}
		
	}
}
