package com.capgemini.mappers;

import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.types.PersonalDataTO;

public class PersonalDataMapper {
	
	public static PersonalDataTO onTO(PersonalDataEmbedded personalData) throws MandatoryValueNotFilledException{
		
		if (personalData == null)
			return null;
		PersonalDataTO mapped = PersonalDataTO.newBuilder()
				.withFirstName(personalData.getFirstName())
				.withLastName(personalData.getLastName())
				.withBirthDate(personalData.getBirthDate())
				.withPhoneNumber(personalData.getPhoneNumber())
				.build();
		return mapped;
	}
	
	public static PersonalDataEmbedded onEntity(PersonalDataTO personalDataTO) throws MandatoryValueNotFilledException{
		
		if (personalDataTO == null)
			return null;
		PersonalDataEmbedded mapped = PersonalDataEmbedded.newBuilder()
				.withFirstName(personalDataTO.getFirstName())
				.withLastName(personalDataTO.getLastName())
				.withBirthDate(personalDataTO.getBirthDate())
				.withPhoneNumber(personalDataTO.getPhoneNumber())
				.build();
		return mapped;
		
	}
	
}
