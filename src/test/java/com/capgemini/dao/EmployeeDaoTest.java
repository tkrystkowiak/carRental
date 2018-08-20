package com.capgemini.dao;

import static org.junit.Assert.*;

import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.AgencyDaoImpl;
import com.capgemini.dao.impl.CarDaoImpl;
import com.capgemini.dao.impl.EmployeeDaoImpl;
import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeSearchCriteria;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.domain.PositionEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeDaoTest {
	
	@Autowired
	EmployeeDaoImpl employeeDao;
	
	@Autowired
	CarDaoImpl carDao;
	
	@Autowired
	AgencyDaoImpl agencyDao;
	
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
		
	}
	
	@Test
	public void testShouldFindOneEmployee(){
		List<EmployeeEntity> actual = employeeDao.findAll();
		assertEquals(1,actual.size());
		
	}
	
	@Test
	public void testShouldFindEmployeeByAgency(){
		EmployeeEntity expected = employeeDao.findAll().get(0);
		List<EmployeeEntity> actual = employeeDao.findEmployeesByAgency(expected.getAgency().getId());
		assertEquals("Albert",actual.get(0).getPersonalData().getFirstName());
	}
	
	@Test
	public void testShouldFindEmployeeByCar() throws MandatoryValueNotFilledException{
		//given
		List<EmployeeEntity> listOfGuardians = employeeDao.findAll();
		CarEntity sampleCar = CarEntity.newBuilder()
				.withType("Sedan")
				.withBrand("BMW")
				.withColor("black")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.withGuardiansList(listOfGuardians)
				.build();
		Long id = carDao.save(sampleCar).getId();
		//when
		List<EmployeeEntity> actual = employeeDao.findEmployeesByCar(id);
		//then
		assertEquals(1,actual.size());
		assertEquals("Albert",actual.get(0).getPersonalData().getFirstName());
	}
	
	@Test
	public void testShouldFindEmployeeByPosition(){
		Long id = employeeDao.findAll().get(0).getId();
		List<EmployeeEntity> actual = employeeDao.findEmployeesByPosition(id);
		assertEquals("Albert",actual.get(0).getPersonalData().getFirstName());
	}
	
	@Test
	public void testShouldFindEmployeeByCriteriaWithAllFilled() throws MandatoryValueNotFilledException{
		//given
		List<EmployeeEntity> listOfGuardians = employeeDao.findAll();
		CarEntity sampleCar = CarEntity.newBuilder()
				.withType("Sedan")
				.withBrand("BMW")
				.withColor("black")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.withGuardiansList(listOfGuardians)
				.build();
		carDao.save(sampleCar);
		EmployeeEntity expected = employeeDao.findAll().get(0);
		EmployeeSearchCriteria criteria = EmployeeSearchCriteria.newBuilder()
				.withAgency(expected.getAgency().getId())
				.withCar(carDao.findAll().get(0).getId())
				.withPosition(expected.getPosition().getId())
				.build();
		List<EmployeeEntity> actual = employeeDao.findEmployeesByCriteria(criteria);
		assertEquals("Albert",actual.get(0).getPersonalData().getFirstName());
	}
	
	@Test
	public void testShouldFindEmployeeByCriteriaWithAgencyAndPositionFilled() throws MandatoryValueNotFilledException{
		//given
		EmployeeEntity expected = employeeDao.findAll().get(0);
		EmployeeSearchCriteria criteria = EmployeeSearchCriteria.newBuilder()
				.withAgency(expected.getAgency().getId())
				.withPosition(expected.getPosition().getId())
				.build();
		//when
		List<EmployeeEntity> actual = employeeDao.findEmployeesByCriteria(criteria);
		//then
		assertEquals("Albert",actual.get(0).getPersonalData().getFirstName());
	}
	
}
