package com.capgemini.mappers;

import org.springframework.stereotype.Component;

import com.capgemini.domain.AddressEmbedded;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.AddressTO;

@Component
public class AddressMapper {
	
	public AddressTO onTO(AddressEmbedded address) throws MandatoryValueNotFilledException{
		
		if (address == null)
			return null;
		AddressTO mapped = AddressTO.newBuilder()
				.withCity(address.getCity())
				.withLocal(address.getLocal())
				.withStreet(address.getStreet())
				.withZipcode(address.getZipcode())
				.build();
		return mapped;
	}
	
	public AddressEmbedded onEntity(AddressTO addressTO) throws MandatoryValueNotFilledException{
		
		if (addressTO == null)
			return null;
		AddressEmbedded mapped = AddressEmbedded.newBuilder()
				.withCity(addressTO.getCity())
				.withLocal(addressTO.getLocal())
				.withStreet(addressTO.getStreet())
				.withZipcode(addressTO.getZipcode())
				.build();
		return mapped;
		
	}
	
}
