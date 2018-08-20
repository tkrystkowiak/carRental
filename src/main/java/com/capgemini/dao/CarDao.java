package com.capgemini.dao;

import java.util.List;


import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;


/**
 * This interface is responsible for operations on CarEntity
 * 
 * @author TKRYSTKO
 *
 */
public interface CarDao extends Dao<CarEntity, Long> {
	
	
	/**
	 * @param Type
	 * @param brand
	 * @return list of car entities with matching type and brand
	 */
	List<CarEntity> findByTypeAndBrand(String Type, String brand);
	
	/**
	 * @param guardianId
	 * @return list of car entities with matching guardian
	 */
	List<CarEntity> findByGuardian(Long guardianId);

	/**
	 * @param numberOfRenters
	 * @return list of car entities with number of distinct renters bigger than given
	 */
	List<CarEntity> findCarsWithGivenNumberOfRenters(Long numberOfRenters);
	
}
