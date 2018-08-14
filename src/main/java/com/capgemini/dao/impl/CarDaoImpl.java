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
	public void assignToGuardian(long carId,EmployeeEntity guardian) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
			 	"update CarEntity car set listOfGuardians = :guardian where id like :car_id)", CarEntity.class);
        	query.setParameter("guardian", guardian);
        	query.setParameter("car_id", carId);
	}

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
	public List<CarEntity> findByGuardian(long guardianId) {
		// TODO Auto-generated method stub
		return null;
	}

}
