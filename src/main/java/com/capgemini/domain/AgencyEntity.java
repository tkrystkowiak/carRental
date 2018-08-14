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
@Table(name = "AGENCIES")
public class AgencyEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Embedded
	private AddressEmbedded address;
	
	@Column(nullable = false, length = 12)
	private String phoneNumber;

	public AgencyEntity() {
	}

	public AgencyEntity(AddressEmbedded address, String phoneNumber) {
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public AddressEmbedded getAddress() {
		return address;
	}

	public void setAddress(AddressEmbedded address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}
	
}
