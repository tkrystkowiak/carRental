package com.capgemini.types;

import java.util.Objects;
import java.util.stream.Stream;

import com.capgemini.domain.MandatoryValueNotFilledException;

public class AddressTO {
	
	private String city;
	private String street;
	private String local;
	private String zipcode;


	public AddressTO(Builder builder) {

		this.city = builder.city;
		this.street = builder.street;
		this.local = builder.local;
		this.zipcode = builder.zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder {

		private String city;
		private String street;
		private String local;
		private String zipcode;

		public Builder withCity(String city) {
			this.city = city;
			return this;
		}

		public Builder withStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder withLocal(String local) {
			this.local = local;
			return this;
		}

		public Builder withZipcode(String zipcode) {
			this.zipcode = zipcode;
			return this;
		}

		public AddressTO build() throws MandatoryValueNotFilledException {
			if (isFilled()) {
				return new AddressTO(this);
			}
			throw new MandatoryValueNotFilledException();

		}

		private boolean isFilled() {
			return Stream.of(city, local, zipcode).noneMatch(Objects::isNull);
		}
	}
	
}
