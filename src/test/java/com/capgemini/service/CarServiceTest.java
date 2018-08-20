package com.capgemini.service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.CarDaoImpl;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.service.impl.CarServiceImpl;
import com.capgemini.types.CarTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class CarServiceTest {
	
	@Autowired
	CarServiceImpl carService;
	
	@Autowired
	CarDaoImpl carDao;
	
	@Test
	public void shouldAddOneCar() throws MandatoryValueNotFilledException{
		//when
		carService.addCar(getSampleCar("Ferrari","Sport"));
		//then
		assertEquals(1,carDao.findAll());
		//assertEquals("Ferrari",carDao.findAll().get(0).getBrand());
	}
	
	private CarTO getSampleCar(String brand, String type) throws MandatoryValueNotFilledException{
		return CarTO.newBuilder()
				.withType(type)
				.withBrand(brand)
				.withColor("black")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
	}
	
}
