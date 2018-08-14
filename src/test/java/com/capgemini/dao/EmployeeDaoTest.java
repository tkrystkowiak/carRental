package com.capgemini.dao;

import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Before
	public void initialization() throws MandatoryValueNotFilledException, ParseException{
		
		EmployeeEntity employee = EmployeeEntity.newBuilder()
				.withPersonalData(PersonalDataEmbedded.newBuilder()
						.withFirstName("Albert")
						.withLastName("Einstein")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withAddress("Krajewskiego 2 01-520 Warszawa")
						.withPhoneNumber("987124098")
						.build())
				.withPosition(new PositionEntity())
				.withAgency(new AgencyEntity())
				.withCarList(new ArrayList<CarEntity>())
				.build();			
	}
	
}
