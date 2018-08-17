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

import com.capgemini.dao.impl.AgencyDaoImpl;
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
public class EmployeeDaoTest {
	
	@Autowired
	EmployeeDaoImpl employeeDao;
	
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
	}
	
	@Test
	public void testShouldFindOneEmployee(){
		List<EmployeeEntity> actual = employeeDao.findAll();
		assertEquals(1,actual.size());
		
	}
	
	@Test
	public void testShouldFindEmployeeByAgency(){
		List<EmployeeEntity> actual = employeeDao.findEmployeesByAgency(1L);
		assertEquals("Albert",actual.get(0).getPersonalData().getFirstName());
	}
	
}
