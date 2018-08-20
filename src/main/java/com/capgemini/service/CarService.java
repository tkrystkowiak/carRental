package com.capgemini.service;

import java.sql.Date;
import java.util.List;

import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.exceptions.NoSuchElementException;
import com.capgemini.types.CarTO;

/**
 * This service handles requests concerning Car
 * 
 * @author TKRYSTKO
 *
 */
public interface CarService {
	
	/**
	 * Adds car to database
	 * 
	 * @param carTO
	 * @throws MandatoryValueNotFilledException
	 */
	void addCar(CarTO carTO) throws MandatoryValueNotFilledException;
	
	/**
	 * Deletes car from database
	 * 
	 * @param carId
	 * @throws NoSuchElementException
	 */
	void deleteCar(long carId) throws NoSuchElementException;
	
	/**
	 * Updates car in database
	 * 
	 * @param carTO
	 * @throws MandatoryValueNotFilledException
	 */
	void updateCar(CarTO carTO) throws MandatoryValueNotFilledException;
	
	/**
	 * Assigns car to employee
	 * 
	 * @param carId
	 * @param guardianId
	 * @throws MandatoryValueNotFilledException
	 */
	void assignToGuardian(long carId, long guardianId) throws MandatoryValueNotFilledException;
	
	/**
	 * @param type
	 * @param brand
	 * @return List of cars with brand and type matching given parameters
	 * @throws MandatoryValueNotFilledException
	 */
	List<CarTO> findByTypeAndBrand(String type, String brand) throws MandatoryValueNotFilledException;
	
	/**
	 * @param guardianId
	 * @return List of cars assigned to given guardian
	 * @throws MandatoryValueNotFilledException
	 */
	List<CarTO> findByGuardian(long guardianId) throws MandatoryValueNotFilledException;

	/**
	 * @param stratDate
	 * @param endDate
	 * @return Number of cars rented in given period
	 */
	Long countCarsRentedInPeriod(Date stratDate, Date endDate);
	
	/**
	 * @param numberOfRenters
	 * @return List of cars with number of distinct renters bigger than given parameter
	 * @throws MandatoryValueNotFilledException
	 */
	List<CarTO> findCarsWithMoreDistinctRentersThan(Long numberOfRenters) throws MandatoryValueNotFilledException;
	
}
