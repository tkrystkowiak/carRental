package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.types.CarTO;

public interface CarService {
	
	void addCar(CarTO carTO) throws MandatoryValueNotFilledException;
	
	void deleteCar(long carId);
	
	void updateCar(CarTO carTO) throws MandatoryValueNotFilledException;
	
	void assignToGuardian(long carId, long guardianId) throws MandatoryValueNotFilledException;
	
	List<CarTO> findByTypeAndBrand(String type, String brand) throws MandatoryValueNotFilledException;
	
	List<CarTO> findByGuardian(long guardianId) throws MandatoryValueNotFilledException;
	
}
