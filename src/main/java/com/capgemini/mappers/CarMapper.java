package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.types.CarTO;

public class CarMapper {

	public static CarTO onTO(CarEntity carEntity) throws MandatoryValueNotFilledException {

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
				.withGuardiansList(EmployeeMapper.onTOs(carEntity.getListOfGuardians()))
				.build();
		return mapped;

	}

	public static CarEntity onEntity(CarTO carTO) throws MandatoryValueNotFilledException {

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
				.withGuardiansList(EmployeeMapper.onEntities(carTO.getListOfGuardians()))
				.build();
		return mapped;

	}
	
	public static List<CarTO> onTOs(List<CarEntity> carEntities) throws MandatoryValueNotFilledException {
		
		List<CarTO> mapped = new ArrayList<CarTO>();
		for(CarEntity car : carEntities){
			mapped.add(onTO(car));
		}
		return mapped;
	}

	public static List<CarEntity> onEntities(List<CarTO> carTOs) throws MandatoryValueNotFilledException {
		
		List<CarEntity> mapped = new ArrayList<CarEntity>();
		for(CarTO car : carTOs){
			mapped.add(onEntity(car));
		}
		return mapped;
		
	}


}
