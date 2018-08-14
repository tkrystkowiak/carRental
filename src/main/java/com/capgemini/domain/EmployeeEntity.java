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
	
	@ManyToMany(mappedBy = "listOfGuardians", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CarEntity> carList;

	public EmployeeEntity() {}

	public EmployeeEntity(EmployeeBuilder builder) {
		this.personalData = builder.personalData;
		this.address = builder.address;
		this.agency = builder.agency;
		this.position = builder.position;
		this.carList = builder.carList;
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

	public List<CarEntity> getCarList() {
		return carList;
	}

	public void setCarList(List<CarEntity> carList) {
		this.carList = carList;
	}

	public Long getId() {
		return id;
	}
	
	public static EmployeeBuilder newBuilder(){
		return new EmployeeBuilder();
	}
	
	public static class EmployeeBuilder {
		
		private PersonalDataEmbedded personalData;
		private AddressEmbedded address;
		private AgencyEntity agency;
		private PositionEntity position;
		private List<CarEntity> carList;
		
		public EmployeeBuilder withPersonalData(PersonalDataEmbedded personalData){
			this.personalData = personalData;
			return this;
		}
		
		public EmployeeBuilder withAddress(AddressEmbedded address){
			this.address = address;
			return this;
		}
		
		public EmployeeBuilder withAgency(AgencyEntity agency){
			this.agency = agency;
			return this;
		}
		
		public EmployeeBuilder withPosition(PositionEntity position){
			this.position = position;
			return this;
		}
		
		public EmployeeBuilder withCarList(List<CarEntity> carList){
			this.carList = carList;
			return this;
		}
		
		public EmployeeEntity build() throws MandatoryValueNotFilledException {
			if(isFilled()){
			return new EmployeeEntity(this);
			}
			throw new MandatoryValueNotFilledException();
			
		}
		
		private boolean isFilled(){
			return Stream.of(personalData,agency,position,carList)
			      .noneMatch(Objects::isNull);
			}
		
	}
}
