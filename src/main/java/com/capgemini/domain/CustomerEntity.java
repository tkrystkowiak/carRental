package com.capgemini.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
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
	
	@Column(nullable = false, length = 50)
	private String firstname;
	
	@Column(nullable = false, length = 50)
	private String lastname;
	
	@Column(nullable = false, length = 150)
	private String address;
	
	@Column(nullable = false, length = 12)
	private String creditCardNumber;
	
	@Column(nullable = false, length = 16)
	private String phoneNumber;
	
	@Column(nullable = false)
	private Date dateOfBirth;
	
	@Column(nullable = false, length = 245)
	private String email;

	public CustomerEntity() {
	}

	public CustomerEntity(String firstname, String lastname, String address, String creditCardNumber,
			String phoneNumber, Date dateOfBirth, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.creditCardNumber = creditCardNumber;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
