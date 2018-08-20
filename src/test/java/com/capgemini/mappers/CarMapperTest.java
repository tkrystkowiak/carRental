package com.capgemini.mappers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
public class CarMapperTest {
	
	@Autowired
	CarMapper carMapper;

	@Test
	public void testShouldMapSingleEntityOnTo() throws MandatoryValueNotFilledException{
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
	public void testShouldMapSingleToOnEntity() throws MandatoryValueNotFilledException{
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
	
	@Test
	public void testShouldMapMultipleEntitiesOnTOs() throws MandatoryValueNotFilledException{
		//given
		CarEntity sampleEntity1 = CarEntity.newBuilder()
				.withId(1L)
				.withType("Sedan")
				.withBrand("BMW")
				.withColor("black")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
		
		CarEntity sampleEntity2 = CarEntity.newBuilder()
				.withId(1L)
				.withType("Sport")
				.withBrand("Tesla")
				.withColor("red")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
		
		List<CarEntity> carList = new ArrayList<CarEntity>();
		carList.add(sampleEntity1);
		carList.add(sampleEntity2);
		//when
		List<CarTO> result = carMapper.onTOs(carList);
		
		//then
		assertEquals(carList.size(),result.size());
		assertEquals("BMW",carList.get(0).getBrand());
		assertEquals("Sedan",carList.get(0).getType());
		assertEquals("Tesla",carList.get(1).getBrand());
		assertEquals("Sport",carList.get(1).getType());
		
	}
	
	@Test
	public void testShouldMapMultipleTOsOnEntities() throws MandatoryValueNotFilledException{
		//given
		CarTO sampleTO1 = CarTO.newBuilder()
				.withId(1L)
				.withType("Sedan")
				.withBrand("BMW")
				.withColor("black")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
		
		CarTO sampleTO2 = CarTO.newBuilder()
				.withId(1L)
				.withType("Sport")
				.withBrand("Tesla")
				.withColor("red")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
		
		List<CarTO> carList = new ArrayList<CarTO>();
		carList.add(sampleTO1);
		carList.add(sampleTO2);
		//when
		List<CarEntity> result = carMapper.onEntities(carList);
		
		//then
		assertEquals(carList.size(),result.size());
		assertEquals("BMW",carList.get(0).getBrand());
		assertEquals("Sedan",carList.get(0).getType());
		assertEquals("Tesla",carList.get(1).getBrand());
		assertEquals("Sport",carList.get(1).getType());
		
	}
}
