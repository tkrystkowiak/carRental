package com.capgemini.mappers;

import com.capgemini.domain.AgencyEntity;
import com.capgemini.types.AgencyTO;

public class AgencyMapper {
	
	public static AgencyTO onTO(AgencyEntity agencyEntity){
		
		if (agencyEntity == null)
			return null;
		AgencyTO mapped = new AgencyTO();
		mapped.setId(agencyEntity.getId());
		mapped.setAddress(agencyEntity.getAddress());
		mapped.setPhoneNumber(agencyEntity.getPhoneNumber());
		return mapped;
	}
	
	public static AgencyEntity onEntity(AgencyTO agencyTO){
		
		if (agencyTO == null)
			return null;
		AgencyEntity mapped = new AgencyEntity();
		mapped.setId(agencyTO.getId());
		mapped.setAddress(agencyTO.getAddress());
		mapped.setPhoneNumber(agencyTO.getPhoneNumber());
		return mapped;
	}
	
}
