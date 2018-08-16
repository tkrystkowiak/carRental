package com.capgemini.types;

import com.capgemini.domain.AddressEmbedded;

public class AgencyTO {
	

	private Long id;
	private AddressEmbedded address;
	private String phoneNumber;
	
	public AgencyTO() {
	}

	public AgencyTO(AddressEmbedded address, String phoneNumber) {
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public AddressEmbedded getAddress() {
		return address;
	}

	public void setId(Long id) {
		this.id = id;
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
