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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.CarDaoImpl;
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
	
	@Test
	public void testShouldHaveOneCarInMemory() throws MandatoryValueNotFilledException {
		CarEntity car = CarEntity.newBuilder().withType("Sedan").withBrand("BMW").withColor("black")
				.withEngineCapacity(3000).withMileage(50000).withHorsePower(234).withYearOfProduction(2012).build();
		carDao.save(car);
		List<CarEntity> carList = carDao.findAll();
		assertEquals(1,carList.size());
		
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
		carDao.assignToGuardian(1L, employee);
		assertEquals("Albert",car2.getListOfGuardians().get(0).getPersonalData().getFirstName());	
	}
}
