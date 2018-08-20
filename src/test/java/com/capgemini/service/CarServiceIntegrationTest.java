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
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.exceptions.NoSuchElementException;
import com.capgemini.mappers.CarMapper;
import com.capgemini.service.impl.CarServiceImpl;
import com.capgemini.types.CarTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class CarServiceIntegrationTest {
	
	@Autowired
	CarServiceImpl carService;
	
	@Autowired
	CarDaoImpl carDao;
	
	@Autowired
	CarMapper carMapper;
	
	@Test
	public void shouldAddOneCar() throws MandatoryValueNotFilledException{
		//when
		carService.addCar(getSampleCar("Ferrari","Sport"));
		//then
		assertEquals(1,carDao.findAll().size());
		assertEquals("Ferrari",carDao.findAll().get(0).getBrand());
	}
	
	
	@Test
	public void shouldDeleteCar() throws MandatoryValueNotFilledException, NoSuchElementException{
		//given
		CarTO sampleCar = getSampleCar("Lamborghini","Sport");
		//when
		carService.addCar(sampleCar);
		Long sampleCarId = carDao.findAll().get(0).getId();
		carService.deleteCar(sampleCarId);
		//then
		assertEquals(0,carDao.findAll().size());
	}
	
	@Test
	public void shouldThrowExceptionWhenThereIsNotCarToDelete() throws MandatoryValueNotFilledException, NoSuchElementException{
		//given
		boolean thrown = false;
		//when
		try{
		carService.deleteCar(1L);
		}
		catch(NoSuchElementException e){
			thrown = true;
		}
		//then
		assertTrue(thrown);
	}
	
	@Test
	public void shouldUpdateCar() throws MandatoryValueNotFilledException, NoSuchElementException{
		//given
		CarTO sampleCar = getSampleCar("Lamborghini","Sport");
		//when
		carService.addCar(sampleCar);
		CarTO toUpdate = carMapper.onTO(carDao.findAll().get(0));
		toUpdate.setBrand("Ferrari");
		carService.updateCar(toUpdate);
		//then
		assertEquals(1,carDao.findAll().size());
		assertEquals("Ferrari",carDao.findAll().get(0).getBrand());
	}
	
	@Test
	public void shouldAddCarWhenThereIsNoCarToUpdate() throws MandatoryValueNotFilledException, NoSuchElementException{
		//given
		CarTO sampleCar = getSampleCar("Lamborghini","Sport");
		//when
		carService.updateCar(sampleCar);
		//then
		assertEquals(1,carDao.findAll().size());
		assertEquals("Lamborghini",carDao.findAll().get(0).getBrand());
		
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
