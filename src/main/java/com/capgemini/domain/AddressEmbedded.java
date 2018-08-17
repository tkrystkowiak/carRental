package com.capgemini.domain;

import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressEmbedded {

	@Column(nullable = false, length = 50)
	private String city;

	@Column(length = 100)
	private String street;

	@Column(length = 10)
	private String local;

	@Column(length = 10)
	private String zipcode;

	public AddressEmbedded() {
	}

	public AddressEmbedded(Builder builder) {

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

		public AddressEmbedded build() throws MandatoryValueNotFilledException {
			if (isFilled()) {
				return new AddressEmbedded(this);
			}
			throw new MandatoryValueNotFilledException();

		}

		private boolean isFilled() {
			return Stream.of(city, street, local, zipcode).noneMatch(Objects::isNull);
		}
	}
}
