package com.capgemini.service;

import java.util.List;

import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

/**
 * This service handles requests concerning Agency
 * 
 * @author TKRYSTKO
 *
 */
public interface AgencyService {
	
	/**
	 * Adds new Agency to database
	 * 
	 * @param agency
	 * @throws MandatoryValueNotFilledException
	 */
	void addAgency(AgencyTO agency) throws MandatoryValueNotFilledException;
	
	/**
	 * Deletes Agency from database
	 * 
	 * @param id
	 */
	void deleteAgency(Long id);
	
	/**
	 * Updates agency in database
	 * 
	 * @param agency
	 * @throws MandatoryValueNotFilledException
	 */
	void updateAgency(AgencyTO agency) throws MandatoryValueNotFilledException;
	
	/**
	 * Assigns Employee to agency
	 * 
	 * @param employeeId
	 * @param agencyId
	 * @throws MandatoryValueNotFilledException
	 */
	void addEmployeeToAgency(Long employeeId, Long agencyId) throws MandatoryValueNotFilledException;
	
	/**
	 * Deletes employee from agency
	 * 
	 * @param employeeId
	 * @param agencyId
	 * @throws MandatoryValueNotFilledException
	 */
	void deleteEmployeeFromAgency(Long employeeId, Long agencyId) throws MandatoryValueNotFilledException;
	
	/**
	 * @param id
	 * @return List of employees assigned to agency
	 * @throws MandatoryValueNotFilledException
	 */
	List<EmployeeTO> findCurrentAgencyEmployees(Long id) throws MandatoryValueNotFilledException;
	
	/**
	 * @param carId
	 * @param agencyId
	 * @return List of employees assigned to given agency and car
	 * @throws MandatoryValueNotFilledException
	 */
	List<EmployeeTO> findCurrentAgencyEmployeesAssignedToCar(Long carId, Long agencyId) throws MandatoryValueNotFilledException;

	
}
