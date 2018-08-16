package com.capgemini.mappers;

import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.types.AddressTO;

public class AddressMapper {
	
	public static AddressTO onTO(AddressEmbedded address) throws MandatoryValueNotFilledException{
		
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
	
	public static AddressEmbedded onEntity(AddressTO addressTO) throws MandatoryValueNotFilledException{
		
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
