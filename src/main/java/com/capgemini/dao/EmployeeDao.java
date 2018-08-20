package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeSearchCriteria;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
	
	List<EmployeeEntity> findEmployeesByAgency(Long agencyId);
	
	List<EmployeeEntity> findEmployeesByCar(Long carId);

	List<EmployeeEntity> findEmployeesByPosition(Long positionId);
	
	List<EmployeeEntity> findEmployeesByCriteria(EmployeeSearchCriteria criteria);
	
	
}
