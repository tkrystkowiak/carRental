package com.capgemini.domain;

public class EmployeeSearchCriteria {
	
	private Long agencyId;
	private Long carId;
	private Long positionId;
	
	public EmployeeSearchCriteria(Builder builder) {
		this.agencyId = builder.agencyId;
		this.carId = builder.carId;
		this.positionId = builder.positionId;
	}

	public Long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		
		private Long agencyId;
		private Long carId;
		private Long positionId;
		
		public Builder withAgency(Long agencyId){
			this.agencyId = agencyId;
			return this;
		}
		
		public Builder withCar(Long carId){
			this.carId = carId;
			return this;
		}
		
		public Builder withPosition(Long positionId){
			this.positionId = positionId;
			return this;
		}
		
		public EmployeeSearchCriteria build(){
			return new EmployeeSearchCriteria(this);
			
		}
	}
	
}
