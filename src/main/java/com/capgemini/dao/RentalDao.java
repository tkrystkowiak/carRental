package com.capgemini.dao;

import java.sql.Date;

import com.capgemini.domain.RentalEntity;

/**
 * This interface is responsible for operations on RentalEntity
 * 
 * @author TKRYSTKO
 *
 */
public interface RentalDao extends Dao <RentalEntity, Long> {
	
	/**
	 * @param startDate
	 * @param endDate
	 * @return Number Of cars being rented in given period
	 */
	Long countCarsRentedInTimePeriod(Date startDate,Date endDate);
	
}
