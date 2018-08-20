package com.capgemini.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeSearchCriteria;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.impl.EmployeeServiceImpl;
import com.capgemini.types.EmployeeTO;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceUnitTest {
	
	@InjectMocks
	EmployeeServiceImpl employeeService;
	
	@Mock
	EmployeeDao employeeDao;
	
	@Mock
	EmployeeMapper employeeMapper;
	
	@Test
	public void shouldFindEmployeesByCriteria() throws MandatoryValueNotFilledException{
		//given
		EmployeeSearchCriteria criteria = EmployeeSearchCriteria.newBuilder().build();
		List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
		List<EmployeeTO> employeeTOs = new ArrayList<EmployeeTO>();
		when(employeeDao.findEmployeesByCriteria(criteria)).thenReturn(employees);
		when(employeeMapper.onTOs(employees)).thenReturn(employeeTOs);
		//when
		List<EmployeeTO> result = employeeService.findEmployeesByCriteria(criteria);
		//then
		assertEquals(employeeTOs,result);
	}
	
}
