package com.capgemini.dao;

import java.util.List;


import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;


public interface CarDao extends Dao<CarEntity, Long> {
	
	List<CarEntity> findByTypeAndBrand(String Type, String brand);
	
	List<CarEntity> findByGuardian(Long guardianId);

	List<CarEntity> findCarsWithGivenNumberOfRenters(Long numberOfRenters);
	
}
