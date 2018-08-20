package com.capgemini.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.RentalDao;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.exceptions.NoSuchElementException;
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
	private final EmployeeMapper employeeMapper;
	private final CarMapper carMapper;
	
	@Autowired
	public CarServiceImpl(CarDao carDao,EmployeeDao employeeDao,RentalDao rentalDao,EmployeeMapper employeeMapper,CarMapper carMapper){
		this.carDao = carDao;
		this.employeeDao = employeeDao;
		this.rentalDao = rentalDao;
		this.employeeMapper = employeeMapper;
		this.carMapper = carMapper;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void addCar(CarTO carTO) throws MandatoryValueNotFilledException {
		
		carDao.save(carMapper.onEntity(carTO));
		
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteCar(long carId) throws NoSuchElementException {
		try{
		carDao.delete(carId);
		}
		catch(ObjectRetrievalFailureException e){
			throw new NoSuchElementException("car");
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void updateCar(CarTO carTO) throws MandatoryValueNotFilledException {
		carDao.update(carMapper.onEntity(carTO));
	}

	@Override
	@Transactional(readOnly = false)
	public void assignToGuardian(long carId, long guardianId) throws MandatoryValueNotFilledException {
		EmployeeTO guardian = employeeMapper.onTO(employeeDao.findOne(guardianId));
		CarTO car = carMapper.onTO(carDao.findOne(carId));
		car.getListOfGuardians().add(guardian);
		carDao.update(carMapper.onEntity(car));
	}

	@Override
	public List<CarTO> findByTypeAndBrand(String type, String brand) throws MandatoryValueNotFilledException {
		return carMapper.onTOs(carDao.findByTypeAndBrand(type, brand));
	}

	@Override
	public List<CarTO> findByGuardian(long guardianId) throws MandatoryValueNotFilledException {
		return carMapper.onTOs(carDao.findByGuardian(guardianId));
	}

	@Override
	public Long countCarsRentedInPeriod(Date startDate,Date endDate) {
		return rentalDao.countCarsRentedInTimePeriod(startDate, endDate);
	}

	@Override
	public List<CarTO> findCarsWithMoreDistinctRentersThan(Long numberOfRenters) throws MandatoryValueNotFilledException {
		
		return carMapper.onTOs(carDao.findCarsWithGivenNumberOfRenters(numberOfRenters));
	}
	
	
	
}
