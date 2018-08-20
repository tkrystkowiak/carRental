package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.EmployeeSearchCriteria;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.types.EmployeeTO;

public interface EmployeeService {
	
	List<EmployeeTO> findEmployeesByCriteria(EmployeeSearchCriteria criteria) throws MandatoryValueNotFilledException;
	
}
