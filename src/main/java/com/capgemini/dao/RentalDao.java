package com.capgemini.dao;

import java.sql.Date;

import com.capgemini.domain.RentalEntity;

public interface RentalDao extends Dao <RentalEntity, Long> {
	
	Long countCarsRentedInTimePeriod(Date startDate,Date endDate);
	
}
