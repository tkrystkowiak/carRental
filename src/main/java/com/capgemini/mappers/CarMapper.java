package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.capgemini.domain.CarEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.CarTO;

/**
 * Maps car objects
 * 
 * @author TKRYSTKO
 *
 */
@Component
public class CarMapper {
	
	private final EmployeeMapper employeeMapper;
	
	public CarMapper(EmployeeMapper employeeMapper) {
		this.employeeMapper = employeeMapper;
	}

	/**
	 * Maps single Entity on single TO
	 * 
	 * @param carEntity
	 * @return mapped car transfer object
	 * @throws MandatoryValueNotFilledException
	 */
	public CarTO onTO(CarEntity carEntity) throws MandatoryValueNotFilledException {

		if (carEntity == null)
			return null;

		CarTO mapped = CarTO.newBuilder()
				.withId(carEntity.getId())
				.withType(carEntity.getType())
				.withBrand(carEntity.getBrand())
				.withColor(carEntity.getColor())
				.withEngineCapacity(carEntity.getEngineCapacity())
				.withHorsePower(carEntity.getHorsePower())
				.withMileage(carEntity.getMileage())
				.withYearOfProduction(carEntity.getYearOfProduction())
				.withGuardiansList(employeeMapper.onTOs(carEntity.getListOfGuardians()))
				.build();
		return mapped;

	}

	/**
	 * maps single TO on single Entity
	 * 
	 * @param carTO
	 * @return mapped car entity
	 * @throws MandatoryValueNotFilledException
	 */
	public CarEntity onEntity(CarTO carTO) throws MandatoryValueNotFilledException {

		if (carTO == null)
			return null;

		CarEntity mapped = CarEntity.newBuilder()
				.withId(carTO.getId())
				.withType(carTO.getType())
				.withBrand(carTO.getBrand())
				.withColor(carTO.getColor())
				.withEngineCapacity(carTO.getEngineCapacity())
				.withHorsePower(carTO.getHorsePower())
				.withMileage(carTO.getMileage())
				.withYearOfProduction(carTO.getYearOfProduction())
				.withGuardiansList(employeeMapper.onEntities(carTO.getListOfGuardians()))
				.build();
		return mapped;

	}
	
	/**
	 * Maps multiple entities on TOs
	 * 
	 * @param carEntities
	 * @return List of mapped car transfer objects
	 * @throws MandatoryValueNotFilledException
	 */
	public List<CarTO> onTOs(List<CarEntity> carEntities) throws MandatoryValueNotFilledException {
		
		List<CarTO> mapped = new ArrayList<CarTO>();
		for(CarEntity car : carEntities){
			mapped.add(onTO(car));
		}
		return mapped;
	}

	/**
	 * Maps multiple TOs on Entities
	 * 
	 * @param carTOs
	 * @return List of mapped car entities
	 * @throws MandatoryValueNotFilledException
	 */
	public List<CarEntity> onEntities(List<CarTO> carTOs) throws MandatoryValueNotFilledException {
		
		List<CarEntity> mapped = new ArrayList<CarEntity>();
		for(CarTO car : carTOs){
			mapped.add(onEntity(car));
		}
		return mapped;
		
	}


}
