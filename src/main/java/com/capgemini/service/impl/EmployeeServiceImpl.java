package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeSearchCriteria;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.EmployeeTO;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao employeeDao;
	private EmployeeMapper employeeMapper;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao,EmployeeMapper employeeMapper) {
		this.employeeDao = employeeDao;
		this.employeeMapper = employeeMapper;
	}

	@Override
	public List<EmployeeTO> findEmployeesByCriteria(EmployeeSearchCriteria criteria) throws MandatoryValueNotFilledException {
		
		return employeeMapper.onTOs(employeeDao.findEmployeesByCriteria(criteria));
		
	}
}
