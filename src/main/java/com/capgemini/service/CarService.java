package com.capgemini.service;

import java.sql.Date;
import java.util.List;

import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.exceptions.NoSuchElementException;
import com.capgemini.types.CarTO;

public interface CarService {
	
	void addCar(CarTO carTO) throws MandatoryValueNotFilledException;
	
	void deleteCar(long carId) throws NoSuchElementException;
	
	void updateCar(CarTO carTO) throws MandatoryValueNotFilledException;
	
	void assignToGuardian(long carId, long guardianId) throws MandatoryValueNotFilledException;
	
	List<CarTO> findByTypeAndBrand(String type, String brand) throws MandatoryValueNotFilledException;
	
	List<CarTO> findByGuardian(long guardianId) throws MandatoryValueNotFilledException;

	Long countCarsRentedInPeriod(Date stratDate, Date endDate);
	
	List<CarTO> findCarsWithMoreDistinctRentersThan(Long numberOfRenters) throws MandatoryValueNotFilledException;
	
}
