package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {


	@Override
	public List<CarEntity> findByTypeAndBrand(String type, String brand) {
		 TypedQuery<CarEntity> query = entityManager.createQuery(
				 	"select car from CarEntity car where upper(car.type) like concat(upper(:type), '%') and"
	                +" upper(car.brand) like concat(upper(:brand), '%')", CarEntity.class);
	        query.setParameter("type", type);
	        query.setParameter("brand", brand);
	        return query.getResultList();
	}

	@Override
	public List<CarEntity> findByGuardian(Long guardianId) {
		EmployeeEntity guardian = entityManager.getReference(EmployeeEntity.class, guardianId);
		TypedQuery<CarEntity> query = entityManager.createQuery(
			 	"select car from CarEntity car where :guardian member of car.listOfGuardians" , CarEntity.class);
		query.setParameter("guardian", guardian); 	
		return query.getResultList();
	}
	
	@Override
	public List<CarEntity> findCarsWithGivenNumberOfRenters(Long numberOfRenters){
		
		TypedQuery<CarEntity> query = entityManager.createQuery(
			 	"select car from CarEntity car "
			 	+ "where car.id in "
			 	+ "(select r.car.id from RentalEntity r group by r.car.id having count(distinct r.customer) > :renters)", CarEntity.class);
		query.setParameter("renters", numberOfRenters);
		return query.getResultList();
		
	}

}
