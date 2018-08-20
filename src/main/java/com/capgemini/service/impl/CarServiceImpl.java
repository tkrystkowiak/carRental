package com.capgemini.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.RentalDao;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
	
	private CarDao carDao;
	private EmployeeDao employeeDao;
	private RentalDao rentalDao;
	
	@Autowired
	public CarServiceImpl(CarDao carDao,EmployeeDao employeeDao,RentalDao rentalDao){
		this.carDao = carDao;
		this.employeeDao = employeeDao;
		this.rentalDao = rentalDao;
	}
	
	@Override
	public void addCar(CarTO carTO) throws MandatoryValueNotFilledException {
		carDao.save(CarMapper.onEntity(carTO));
	}

	@Override
	public void deleteCar(long carId) {
		carDao.delete(carId);
	}

	@Override
	public void updateCar(CarTO carTO) throws MandatoryValueNotFilledException {
		carDao.update(CarMapper.onEntity(carTO));
	}

	@Override
	public void assignToGuardian(long carId, long guardianId) throws MandatoryValueNotFilledException {
		EmployeeTO guardian = EmployeeMapper.onTO(employeeDao.findOne(guardianId));
		CarTO car = CarMapper.onTO(carDao.findOne(carId));
		car.getListOfGuardians().add(guardian);
		carDao.update(CarMapper.onEntity(car));
	}

	@Override
	public List<CarTO> findByTypeAndBrand(String type, String brand) throws MandatoryValueNotFilledException {
		return CarMapper.onTOs(carDao.findByTypeAndBrand(type, brand));
	}

	@Override
	public List<CarTO> findByGuardian(long guardianId) throws MandatoryValueNotFilledException {
		
		return CarMapper.onTOs(carDao.findByGuardian(guardianId));
	}

	@Override
	public Long countCarsRentedInPeriod(Date startDate,Date endDate) {
		return rentalDao.countCarsRentedInTimePeriod(startDate, endDate);
	}

	@Override
	public List<CarTO> findCarsWithMoreDistinctRentersThan(Long numberOfRenters) throws MandatoryValueNotFilledException {
		
		return CarMapper.onTOs(carDao.findCarsWithGivenNumberOfRenters(numberOfRenters));
	}
	
	
	
}
