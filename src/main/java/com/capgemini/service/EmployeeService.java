package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.EmployeeSearchCriteria;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.EmployeeTO;

/**
 * This service handles requests concerning employees
 * 
 * @author TKRYSTKO
 *
 */
public interface EmployeeService {
	
	/**
	 * @param criteria
	 * @return List of employees matching given criteria
	 * @throws MandatoryValueNotFilledException
	 */
	List<EmployeeTO> findEmployeesByCriteria(EmployeeSearchCriteria criteria) throws MandatoryValueNotFilledException;
	
}
