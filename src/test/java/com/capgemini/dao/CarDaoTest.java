package com.capgemini.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.CarDaoImpl;
import com.capgemini.dao.impl.EmployeeDaoImpl;
import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.domain.PositionEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CarDaoTest {
	
	@Autowired
	CarDaoImpl carDao;
	
	@Autowired
	EmployeeDaoImpl employeeDao;
	
	@Before
	public void initialization() throws MandatoryValueNotFilledException, ParseException{
		AddressEmbedded sampleAddress = AddressEmbedded.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();
		
		AgencyEntity sampleAgency = new AgencyEntity(sampleAddress,"141543789");
		
		PositionEntity samplePosition = new PositionEntity("developer");
		EmployeeEntity employee = EmployeeEntity.newBuilder()
				.withPersonalData(PersonalDataEmbedded.newBuilder()
						.withFirstName("Albert")
						.withLastName("Einstein")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withPhoneNumber("987124098")
						.build())
				.withAddress(sampleAddress)
				.withPosition(samplePosition)
				.withAgency(sampleAgency)
				.build();
		employeeDao.save(employee);
		List<EmployeeEntity> listOfGuardians = new ArrayList<EmployeeEntity>();
		listOfGuardians.add(employee);
		
		CarEntity car = CarEntity.newBuilder()
				.withType("Sedan")
				.withBrand("BMW")
				.withColor("black")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.withGuardiansList(listOfGuardians)
				.build();
		carDao.save(car);
	}
	
	@Test
	public void testShouldHaveOneCarInMemory() throws MandatoryValueNotFilledException {
		List<CarEntity> carList = carDao.findAll();
		assertEquals(1,carList.size());	
	}
	
	@Test
	public void testShouldThrowException()  {
		boolean thrown = false;
		try {
			CarEntity car = CarEntity.newBuilder().withType("Sedan").withBrand("BMW").withColor("black").build();
		} catch (MandatoryValueNotFilledException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testShouldFindCarByEmployee() throws MandatoryValueNotFilledException, ParseException{
		
		CarEntity car = carDao.findAll().get(0);
		assertEquals(car,carDao.findByGuardian(1L).get(0));
	}
	
	@Test
	public void testShouldFindCarByBrandAndType() throws MandatoryValueNotFilledException {
		CarEntity car = CarEntity.newBuilder().withType("Sedan").withBrand("BMW").withColor("black")
				.withEngineCapacity(3000).withMileage(50000).withHorsePower(234).withYearOfProduction(2012).build();
		carDao.save(car);
		List<CarEntity> carList = carDao.findByTypeAndBrand("Sedan", "BMW");
		assertEquals("BMW",carList.get(0).getBrand());
	}
	
	@Test
	public void testShouldUpdateEmployeeInCar() throws MandatoryValueNotFilledException, ParseException {
		CarEntity car = CarEntity.newBuilder()
				.withType("Sedan")
				.withBrand("BMW")
				.withColor("black")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
		carDao.save(car);
		EmployeeEntity employee = EmployeeEntity.newBuilder()
				.withPersonalData(PersonalDataEmbedded.newBuilder()
						.withFirstName("Albert")
						.withLastName("Einstein")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withPhoneNumber("987124098")
						.build())
				.withAddress(AddressEmbedded.newBuilder()
						.withCity("Warszawa")
						.withLocal("2")
						.withStreet("Krajewskiego")
						.withZipcode("01-520")
						.build())
				.withPosition(new PositionEntity())
				.withAgency(new AgencyEntity())
				.build();
		CarEntity car2 = carDao.findOne(1L);
		assertEquals("Albert",car2.getListOfGuardians().get(0).getPersonalData().getFirstName());	
	}
}
