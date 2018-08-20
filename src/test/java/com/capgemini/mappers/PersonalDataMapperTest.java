package com.capgemini.mappers;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.AddressTO;
import com.capgemini.types.PersonalDataTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalDataMapperTest {
	
	@Autowired
	PersonalDataMapper personalDataMapper;
	
	@Test
	public void testShouldMapOnSingleTo() throws MandatoryValueNotFilledException, ParseException{
		//given
		PersonalDataEmbedded personalData = PersonalDataEmbedded.newBuilder()
				.withFirstName("Albert")
				.withLastName("Einstein")
				.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
				.withPhoneNumber("987124098")
				.build();
		//when
		PersonalDataTO result= personalDataMapper.onTO(personalData);
		//then
		assertEquals(personalData.getFirstName(),result.getFirstName());
		assertEquals(personalData.getLastName(),result.getLastName());
		assertEquals(personalData.getBirthDate(),result.getBirthDate());
		assertEquals(personalData.getPhoneNumber(),result.getPhoneNumber());
	}
	
	@Test
	public void testShouldMapOnSingleEmbedded() throws MandatoryValueNotFilledException, ParseException{
		//given
		PersonalDataTO personalData = PersonalDataTO.newBuilder()
				.withFirstName("Albert")
				.withLastName("Einstein")
				.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
				.withPhoneNumber("987124098")
				.build();
		//when
		PersonalDataEmbedded result= personalDataMapper.onEntity(personalData);
		//then
		assertEquals(personalData.getFirstName(),result.getFirstName());
		assertEquals(personalData.getLastName(),result.getLastName());
		assertEquals(personalData.getBirthDate(),result.getBirthDate());
		assertEquals(personalData.getPhoneNumber(),result.getPhoneNumber());
	}
	
}
