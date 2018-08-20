package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.EmployeeTO;

/**
 * Maps employee objects
 * 
 * @author TKRYSTKO
 *
 */
@Component
public class EmployeeMapper {
	
	private final AgencyMapper agencyMapper;
	private final AddressMapper addressMapper;
	private final PersonalDataMapper personalDataMapper;
	private final PositionMapper positionMapper;
	
	@Autowired
	public EmployeeMapper(AgencyMapper agencyMapper, AddressMapper addressMapper,PersonalDataMapper personalDataMapper,
			PositionMapper positionMapper) {
		this.agencyMapper = agencyMapper;
		this.addressMapper = addressMapper;
		this.personalDataMapper = personalDataMapper;
		this.positionMapper = positionMapper;
	}

	/**
	 * Maps single Entity on TO
	 * 
	 * @param employeeEntity
	 * @return mapped employee transfer object
	 * @throws MandatoryValueNotFilledException
	 */
	public EmployeeTO onTO(EmployeeEntity employeeEntity) throws MandatoryValueNotFilledException {

		if (employeeEntity == null)
			return null;

		EmployeeTO mapped = EmployeeTO.newBuilder()
				.withId(employeeEntity.getId())
				.withPersonalData(personalDataMapper.onTO(employeeEntity.getPersonalData()))
				.withAddress(addressMapper.onTO(employeeEntity.getAddress()))
				.withPosition(positionMapper.onTO(employeeEntity.getPosition()))
				.withAgency(agencyMapper.onTO(employeeEntity.getAgency()))
				.build();
		return mapped;

	}
	
	/**
	 * Maps single TO on Entity
	 * 
	 * @param employeeTO
	 * @return mapped employee entity
	 * @throws MandatoryValueNotFilledException
	 */
	public EmployeeEntity onEntity(EmployeeTO employeeTO) throws MandatoryValueNotFilledException {

		if (employeeTO == null)
			return null;

		EmployeeEntity mapped = EmployeeEntity.newBuilder()
				.withId(employeeTO.getId())
				.withPersonalData(personalDataMapper.onEntity(employeeTO.getPersonalData()))
				.withAddress(addressMapper.onEntity(employeeTO.getAddress()))
				.withPosition(positionMapper.onEntity(employeeTO.getPosition()))
				.withAgency(agencyMapper.onEntity(employeeTO.getAgency()))
				.build();
		return mapped;
	
	}
	
	/**
	 * Maps multiple Entities on TOs
	 * 
	 * @param employeeEntities
	 * @return List of mapped employee transfer objects
	 * @throws MandatoryValueNotFilledException
	 */
	public List<EmployeeTO> onTOs(List<EmployeeEntity> employeeEntities) throws MandatoryValueNotFilledException {
		
		if (employeeEntities == null)
			return null;
		
		List<EmployeeTO> mapped = new ArrayList<EmployeeTO>();
		for(EmployeeEntity employee : employeeEntities){
			mapped.add(onTO(employee));
		}
		return mapped;
	}

	/**
	 * Maps multiple TOs on Entities
	 * 
	 * @param employeeTOs
	 * @return List of mapped employee entities
	 * @throws MandatoryValueNotFilledException
	 */
	public List<EmployeeEntity> onEntities(List<EmployeeTO> employeeTOs) throws MandatoryValueNotFilledException {
		
		if (employeeTOs == null)
			return null;
		
		List<EmployeeEntity> mapped = new ArrayList<EmployeeEntity>();
		for(EmployeeTO employee : employeeTOs){
			mapped.add(onEntity(employee));
		}
		return mapped;	
	}
	
	/**
	 * Maps multiple Entities on ids
	 * 
	 * @param employeeEntities
	 * @return List of employees's ids
	 */
	public List<Long> onIds(List<EmployeeEntity> employeeEntities) {
		
		if (employeeEntities == null)
			return null;
		
		List<Long> mapped = new ArrayList<Long>();
		for(EmployeeEntity employee : employeeEntities){
			mapped.add(employee.getId());
		}
		return mapped;
	}
	
	
	
}
