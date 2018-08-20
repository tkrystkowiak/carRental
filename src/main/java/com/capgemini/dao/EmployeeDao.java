package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeSearchCriteria;

/**
 * This interface is responsible for operations on EmployeeEntity
 * 
 * @author TKRYSTKO
 *
 */
public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
	
	/**
	 * @param agencyId
	 * @return List of employees assigned to given agency
	 */
	List<EmployeeEntity> findEmployeesByAgency(Long agencyId);
	
	/**
	 * @param carId
	 * @return List of employees assigned to given agency
	 */
	List<EmployeeEntity> findEmployeesByCar(Long carId);

	/**
	 * @param positionId
	 * @return List of employees with given position
	 */
	List<EmployeeEntity> findEmployeesByPosition(Long positionId);
	
	/**
	 * @param criteria
	 * @return List of employees matching given criteria
	 */
	List<EmployeeEntity> findEmployeesByCriteria(EmployeeSearchCriteria criteria);
	
	
}
