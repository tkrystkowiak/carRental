package com.capgemini.mappers;

import org.springframework.stereotype.Component;

import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.PersonalDataTO;

/**
 * Maps personalData objects
 * 
 * @author TKRYSTKO
 *
 */
@Component
public class PersonalDataMapper {
	
	/**
	 * Maps single embedded object on TO
	 * 
	 * @param personalData
	 * @return mapped PersonalData transfer object
	 * @throws MandatoryValueNotFilledException
	 */
	public PersonalDataTO onTO(PersonalDataEmbedded personalData) throws MandatoryValueNotFilledException{
		
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
	
	/**
	 * Maps single TO on embedded object
	 * 
	 * @param personalDataTO
	 * @return mapped PersonalData embedded object
	 * @throws MandatoryValueNotFilledException
	 */
	public PersonalDataEmbedded onEntity(PersonalDataTO personalDataTO) throws MandatoryValueNotFilledException{
		
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
