package com.capgemini.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Embedded
	private PersonalDataEmbedded personalData;
	
	@Embedded
	private AddressEmbedded address;
	
	@Column(nullable = false, length = 12)
	private String creditCardNumber;
	
	public AddressEmbedded getAddress() {
		return address;
	}

	public void setAddress(AddressEmbedded address) {
		this.address = address;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false, length = 245)
	private String email;

	public CustomerEntity() {
	}

	public CustomerEntity(Builder builder) {
		this.personalData = builder.personalData;
		this.address = builder.address;
		this.creditCardNumber = builder.creditCardNumber;
		this.email = builder.email;
	}
	
	public PersonalDataEmbedded getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalDataEmbedded personalData) {
		this.personalData = personalData;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}
	
	public static class Builder{
		
		private PersonalDataEmbedded personalData;
		private AddressEmbedded address;
		private String email;
		private String creditCardNumber;
		
		public Builder withPersonalData(PersonalDataEmbedded personalData){
			this.personalData = personalData;
			return this;
		}
		
		public Builder withAddres(AddressEmbedded address){
			this.address = address;
			return this;
		}
		
		public Builder withEmail(String email){
			this.email = email;
			return this;
		}
		
		public Builder withCreditCardNumber(String creditCardNumber){
			this.creditCardNumber = creditCardNumber;
			return this;
		}
		
		public CustomerEntity build(){
			return new CustomerEntity(this);
		}
		
	}
}
