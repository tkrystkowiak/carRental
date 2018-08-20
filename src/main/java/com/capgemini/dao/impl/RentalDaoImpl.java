package com.capgemini.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.RentalDao;
import com.capgemini.domain.RentalEntity;

@Repository
public class RentalDaoImpl extends AbstractDao<RentalEntity,Long> implements RentalDao  {

	@Override
	public Long countCarsRentedInTimePeriod(Date startDate, Date endDate) {
		
		TypedQuery<Long> query = entityManager.createQuery(
			 	"select count(rental) from RentalEntity rental where rentDate < :startDate and returnDate > :endDate",Long.class);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.getSingleResult();
	}
	
	public List<Long> countNumberOfDistinctRenters() {
		
		TypedQuery<Long> query = entityManager.createQuery(
			 	"select r.car.id from RentalEntity r group by r.car.id having count(distinct r.customer) > 10", Long.class);
		return query.getResultList();
	}
}
