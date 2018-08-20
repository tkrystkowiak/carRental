package com.capgemini.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeSearchCriteria;
import com.capgemini.domain.PositionEntity;

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

	@Override
	public List<EmployeeEntity> findEmployeesByPosition(Long positionId) {
		PositionEntity position = entityManager.getReference(PositionEntity.class, positionId);
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
			 	"select employee from EmployeeEntity employee where position =:position" , EmployeeEntity.class);
		query.setParameter("position", position);
		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeesByCar(Long carId) {
		CarEntity car = entityManager.getReference(CarEntity.class, carId);
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
			 	"select employee from EmployeeEntity employee where :car member of employee.carList" , EmployeeEntity.class);
		query.setParameter("car", car);
		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeesByCriteria(EmployeeSearchCriteria criteria) {
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select employee from EmployeeEntity employee where ");
		
		if(criteria.getAgencyId()!=null){
			queryBuilder.append("agency =:agency and ");
			AgencyEntity agency = entityManager.getReference(AgencyEntity.class, criteria.getAgencyId());
			parameters.put("agency", agency);
		}
		
		if(criteria.getCarId()!=null){
			queryBuilder.append(":car member of employee.carList and ");
			CarEntity car = entityManager.getReference(CarEntity.class, criteria.getCarId());
			parameters.put("car", car);
		}
		
		if(criteria.getPositionId()!=null){
			queryBuilder.append("position =:position");
			PositionEntity position = entityManager.getReference(PositionEntity.class, criteria.getPositionId());
			parameters.put("position", position);
		}
		
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(queryBuilder.toString(), EmployeeEntity.class);
		parameters.keySet().forEach(key -> query.setParameter(key,parameters.get(key)));
		return query.getResultList();
	}

}
