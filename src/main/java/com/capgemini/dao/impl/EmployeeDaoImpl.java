package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {
	
	@Override
	public List<EmployeeEntity> findEmployeesByAgency(Long agencyId) {
		AgencyEntity agency = entityManager.getReference(AgencyEntity.class, agencyId);
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
			 	"select employee from EmployeeEntity employee where agency =:agency" , EmployeeEntity.class);
		query.setParameter("agency", agency); 
		return query.getResultList();
	}

}
