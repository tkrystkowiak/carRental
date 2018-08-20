package com.capgemini.service.impl;

import java.util.List;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeSearchCriteria;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.EmployeeTO;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao employeeDao;
	
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public List<EmployeeTO> findEmployeesByCriteria(EmployeeSearchCriteria criteria) throws MandatoryValueNotFilledException {
		
		return EmployeeMapper.onTOs(employeeDao.findEmployeesByCriteria(criteria));
		
	}
}
