package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.AgencyDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class AgencyDaoImpl extends AbstractDao<AgencyEntity, Long> implements AgencyDao{

	@Override
	public void addEmployee(EmployeeEntity employee) {
		
		
	}

	@Override
	public void deleteEmployee(EmployeeEntity employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmployeeEntity> findCurrentEmployees(long id) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
			 	"select  from CarEntity car where :guardian member of listOfGuardians" , CarEntity.class);
		return null;
	}

	@Override
	public List<EmployeeEntity> findAllEmployeesAssignedToCar(long AgencyId, long CarId) {
		// TODO Auto-generated method stub
		return null;
	}

}
