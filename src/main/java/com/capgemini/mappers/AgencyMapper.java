package com.capgemini.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.domain.AgencyEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.AgencyTO;

/**
 * Maps agency objects
 * 
 * @author TKRYSTKO
 *
 */
@Component
public class AgencyMapper {
	
	private AddressMapper addressMapper;
	
	
	@Autowired
	public AgencyMapper(AddressMapper addressMapper) {
		this.addressMapper = addressMapper;
	}

	/**
	 * Maps single Entity on Single TO
	 * 
	 * @param agencyEntity
	 * @return mapped Agency transfer object
	 * @throws MandatoryValueNotFilledException
	 */
	public AgencyTO onTO(AgencyEntity agencyEntity) throws MandatoryValueNotFilledException{
		
		if (agencyEntity == null)
			return null;
		AgencyTO mapped = new AgencyTO();
		mapped.setId(agencyEntity.getId());
		mapped.setAddress(addressMapper.onTO(agencyEntity.getAddress()));
		mapped.setPhoneNumber(agencyEntity.getPhoneNumber());
		return mapped;
	}
	
	/**
	 * Maps single TO on single Entity
	 * 
	 * @param agencyTO
	 * @return mapped Agency entity
	 * @throws MandatoryValueNotFilledException
	 */
	public AgencyEntity onEntity(AgencyTO agencyTO) throws MandatoryValueNotFilledException{
		
		if (agencyTO == null)
			return null;
		AgencyEntity mapped = new AgencyEntity();
		mapped.setId(agencyTO.getId());
		mapped.setAddress(addressMapper.onEntity(agencyTO.getAddress()));
		mapped.setPhoneNumber(agencyTO.getPhoneNumber());
		return mapped;
	}
	
}
