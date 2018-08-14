package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.EmployeeEntity;

public interface AgencyDao extends Dao<AgencyEntity, Long> {
	
	void addEmployee(EmployeeEntity employee);
	
	void deleteEmployee(EmployeeEntity employee);
	
	List<EmployeeEntity> findCurrentEmployees(long id);
	
	List<EmployeeEntity> findAllEmployeesAssignedToCar(long AgencyId, long CarId);
}
