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
	
	@Column(nullable = false, length = 12)
	private String creditCardNumber;
	
	@Column(nullable = false, length = 245)
	private String email;

	public CustomerEntity() {
	}

	public CustomerEntity(PersonalDataEmbedded personalData, String creditCardNumber, String email) {
		this.personalData = personalData;
		this.creditCardNumber = creditCardNumber;
		this.email = email;
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
	
}
