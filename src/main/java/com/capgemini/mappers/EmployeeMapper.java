package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.types.EmployeeTO;

public class EmployeeMapper {
	
	public static EmployeeTO onTO(EmployeeEntity employeeEntity) throws MandatoryValueNotFilledException {

		if (employeeEntity == null)
			return null;

		EmployeeTO mapped = EmployeeTO.newBuilder()
				.withId(employeeEntity.getId())
				.withPersonalData(PersonalDataMapper.onTO(employeeEntity.getPersonalData()))
				.withAddress(AddressMapper.onTO(employeeEntity.getAddress()))
				.withPosition(PositionMapper.onTO(employeeEntity.getPosition()))
				.withAgency(AgencyMapper.onTO(employeeEntity.getAgency()))
				.build();
		return mapped;

	}
	
	public static EmployeeEntity onEntity(EmployeeTO employeeTO) throws MandatoryValueNotFilledException {

		if (employeeTO == null)
			return null;

		EmployeeEntity mapped = EmployeeEntity.newBuilder()
				.withId(employeeTO.getId())
				.withPersonalData(PersonalDataMapper.onEntity(employeeTO.getPersonalData()))
				.withAddress(AddressMapper.onEntity(employeeTO.getAddress()))
				.withPosition(PositionMapper.onEntity(employeeTO.getPosition()))
				.withAgency(AgencyMapper.onEntity(employeeTO.getAgency()))
				.build();
		return mapped;
	
	}
	
	public static List<EmployeeTO> onTOs(List<EmployeeEntity> employeeEntities) throws MandatoryValueNotFilledException {
		
		if (employeeEntities == null)
			return null;
		
		List<EmployeeTO> mapped = new ArrayList<EmployeeTO>();
		for(EmployeeEntity employee : employeeEntities){
			mapped.add(onTO(employee));
		}
		return mapped;
	}

	public static List<EmployeeEntity> onEntities(List<EmployeeTO> employeeTOs) throws MandatoryValueNotFilledException {
		
		if (employeeTOs == null)
			return null;
		
		List<EmployeeEntity> mapped = new ArrayList<EmployeeEntity>();
		for(EmployeeTO employee : employeeTOs){
			mapped.add(onEntity(employee));
		}
		return mapped;	
	}
	
	public static List<Long> onIds(List<EmployeeEntity> employeeEntities) {
		
		if (employeeEntities == null)
			return null;
		
		List<Long> mapped = new ArrayList<Long>();
		for(EmployeeEntity employee : employeeEntities){
			mapped.add(employee.getId());
		}
		return mapped;
	}
	
	
	
}
