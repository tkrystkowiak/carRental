package com.capgemini.types;

import java.sql.Date;
import java.util.Objects;
import java.util.stream.Stream;

import com.capgemini.domain.MandatoryValueNotFilledException;

public class PersonalDataTO {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Date birthDate;

	public PersonalDataTO(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.phoneNumber = builder.phoneNumber;
		this.birthDate = builder.birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {

		private String firstName;
		private String lastName;
		private String phoneNumber;
		private Date birthDate;

		public Builder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder withPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder withBirthDate(Date birthDate) {
			this.birthDate = birthDate;
			return this;
		}

		public PersonalDataTO build() throws MandatoryValueNotFilledException {
			if (isFilled()) {
				return new PersonalDataTO(this);
			}
			throw new MandatoryValueNotFilledException();

		}

		private boolean isFilled() {
			return Stream.of(firstName, lastName, phoneNumber, birthDate).noneMatch(Objects::isNull);
		}

	}
}
