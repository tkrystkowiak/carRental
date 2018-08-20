package com.capgemini.mappers;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.CarEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.CarTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CarMapperTest {
	
	@Autowired
	CarMapper carMapper;

	@Test
	public void shouldMapSingleEntityOnTo() throws MandatoryValueNotFilledException{
		//given
		CarEntity sampleEntity = CarEntity.newBuilder()
				.withId(1L)
				.withType("Sedan")
				.withBrand("BMW")
				.withColor("black")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
		
		CarTO sampleTO = carMapper.onTO(sampleEntity);
		
		assertEquals(sampleEntity.getId(),sampleTO.getId());
		assertEquals(sampleEntity.getHorsePower(),sampleTO.getHorsePower());
		assertEquals(sampleEntity.getBrand(),sampleTO.getBrand());
		assertEquals(sampleEntity.getType(),sampleTO.getType());
		assertEquals(sampleEntity.getMileage(),sampleTO.getMileage());
		assertEquals(sampleEntity.getYearOfProduction(),sampleTO.getYearOfProduction());
		assertEquals(sampleEntity.getListOfGuardians(),sampleTO.getListOfGuardians());
	}
	
	@Test
	public void shouldMapSingleToOnEntity() throws MandatoryValueNotFilledException{
		//given
		CarTO sampleTO = CarTO.newBuilder()
				.withId(1L)
				.withType("Sedan")
				.withBrand("BMW")
				.withColor("black")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
		
		CarEntity sampleEntity = carMapper.onEntity(sampleTO);
		
		assertEquals(sampleEntity.getId(),sampleTO.getId());
		assertEquals(sampleEntity.getHorsePower(),sampleTO.getHorsePower());
		assertEquals(sampleEntity.getBrand(),sampleTO.getBrand());
		assertEquals(sampleEntity.getType(),sampleTO.getType());
		assertEquals(sampleEntity.getMileage(),sampleTO.getMileage());
		assertEquals(sampleEntity.getYearOfProduction(),sampleTO.getYearOfProduction());
		assertEquals(sampleEntity.getListOfGuardians(),sampleTO.getListOfGuardians());
	}
	
}
