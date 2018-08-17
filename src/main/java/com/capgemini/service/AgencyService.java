package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

public interface AgencyService {
	
	void addAgency(AgencyTO agency);
	
	void deleteAgency(Long id);
	
	void updateAgency(AgencyTO agency);
	
	void addEmployeeToAgency(Long employeeId, Long agencyId) throws MandatoryValueNotFilledException;
	
	void deleteEmployeeFromAgency(Long employeeId, Long agencyId) throws MandatoryValueNotFilledException;
	
	List<EmployeeTO> findCurrentAgencyEmployees(Long id) throws MandatoryValueNotFilledException;
	
	List<EmployeeTO> findCurrentAgencyEmployeesAssignedToCar(Long carId, Long agencyId) throws MandatoryValueNotFilledException;

	
}