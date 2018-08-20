package com.capgemini.mappers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.domain.PositionEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.AddressTO;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PersonalDataTO;
import com.capgemini.types.PositionTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeMapperTest {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	
	@Test
	public void testShouldMapSingleOnTO() throws MandatoryValueNotFilledException, ParseException{
		//given
		AddressEmbedded sampleAddress = AddressEmbedded.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();

		AgencyEntity sampleAgency = new AgencyEntity(sampleAddress,"141543789");
		
		PositionEntity samplePosition = new PositionEntity("developer");
		samplePosition.setId(1L);
		
		EmployeeEntity employee = EmployeeEntity.newBuilder()
				.withId(1L)
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
		//when
		EmployeeTO result = employeeMapper.onTO(employee);
		//then
		assertEquals(employee.getId(),result.getId());
		assertEquals(employee.getPersonalData().getFirstName(),result.getPersonalData().getFirstName());
		assertEquals(employee.getPosition().getTitle(),result.getPosition().getTitle());
		assertEquals(employee.getAddress().getCity(),result.getAddress().getCity());
		assertEquals(employee.getAgency().getId(),result.getAgency().getId());
		
	}
	
	@Test
	public void testShouldMapSingleOnEntity() throws MandatoryValueNotFilledException, ParseException{
		//given
		AddressTO sampleAddress = AddressTO.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();

		AgencyTO sampleAgency = new AgencyTO(sampleAddress,"141543789");
		
		PositionTO samplePosition = new PositionTO(1L, "developer");
		
		EmployeeTO employee = EmployeeTO.newBuilder()
				.withId(1L)
				.withPersonalData(PersonalDataTO.newBuilder()
						.withFirstName("Albert")
						.withLastName("Einstein")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withPhoneNumber("987124098")
						.build())
				.withAddress(sampleAddress)
				.withPosition(samplePosition)
				.withAgency(sampleAgency)
				.build();
		//when
		EmployeeEntity result = employeeMapper.onEntity(employee);
		//then
		assertEquals(employee.getId(),result.getId());
		assertEquals(employee.getPersonalData().getFirstName(),result.getPersonalData().getFirstName());
		assertEquals(employee.getPosition().getTitle(),result.getPosition().getTitle());
		assertEquals(employee.getAddress().getCity(),result.getAddress().getCity());
		assertEquals(employee.getAgency().getId(),result.getAgency().getId());
		
	}
	
	@Test
	public void testShouldMapMultipleOnTOs() throws MandatoryValueNotFilledException, ParseException{
		//given
		AddressEmbedded sampleAddress = AddressEmbedded.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();

		AgencyEntity sampleAgency = new AgencyEntity(sampleAddress,"141543789");
		
		PositionEntity samplePosition = new PositionEntity("developer");
		samplePosition.setId(1L);
		
		EmployeeEntity employee1 = EmployeeEntity.newBuilder()
				.withId(1L)
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
		
		EmployeeEntity employee2 = EmployeeEntity.newBuilder()
				.withId(2L)
				.withPersonalData(PersonalDataEmbedded.newBuilder()
						.withFirstName("Ramzes")
						.withLastName("Faraon")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withPhoneNumber("987124098")
						.build())
				.withAddress(sampleAddress)
				.withPosition(samplePosition)
				.withAgency(sampleAgency)
				.build();
		List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
		employees.add(employee1);
		employees.add(employee2);	
		//when
		
		List<EmployeeTO>result = employeeMapper.onTOs(employees);
		//then
		assertEquals(2,result.size());
		assertEquals(employees.get(0).getId(),result.get(0).getId());
		assertEquals(employees.get(1).getId(),result.get(1).getId());
	}
	
	@Test
	public void testShouldMapMultipleOnEntities() throws MandatoryValueNotFilledException, ParseException{
		//given
		AddressTO sampleAddress = AddressTO.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();

		AgencyTO sampleAgency = new AgencyTO(sampleAddress,"141543789");
		
		PositionTO samplePosition = new PositionTO(1L,"developer");
		
		EmployeeTO employee1 = EmployeeTO.newBuilder()
				.withId(1L)
				.withPersonalData(PersonalDataTO.newBuilder()
						.withFirstName("Albert")
						.withLastName("Einstein")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withPhoneNumber("987124098")
						.build())
				.withAddress(sampleAddress)
				.withPosition(samplePosition)
				.withAgency(sampleAgency)
				.build();
		
		EmployeeTO employee2 = EmployeeTO.newBuilder()
				.withId(2L)
				.withPersonalData(PersonalDataTO.newBuilder()
						.withFirstName("Ramzes")
						.withLastName("Faraon")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withPhoneNumber("987124098")
						.build())
				.withAddress(sampleAddress)
				.withPosition(samplePosition)
				.withAgency(sampleAgency)
				.build();
		List<EmployeeTO> employees = new ArrayList<EmployeeTO>();
		employees.add(employee1);
		employees.add(employee2);	
		//when
		
		List<EmployeeEntity>result = employeeMapper.onEntities(employees);
		//then
		assertEquals(2,result.size());
		assertEquals(employees.get(0).getId(),result.get(0).getId());
		assertEquals(employees.get(1).getId(),result.get(1).getId());
	}
	
	@Test
	public void testShouldMapMultipleOnIds() throws MandatoryValueNotFilledException, ParseException{
		//given
		AddressEmbedded sampleAddress = AddressEmbedded.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();

		AgencyEntity sampleAgency = new AgencyEntity(sampleAddress,"141543789");
		
		PositionEntity samplePosition = new PositionEntity("developer");
		samplePosition.setId(1L);
		
		EmployeeEntity employee1 = EmployeeEntity.newBuilder()
				.withId(1L)
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
		
		EmployeeEntity employee2 = EmployeeEntity.newBuilder()
				.withId(2L)
				.withPersonalData(PersonalDataEmbedded.newBuilder()
						.withFirstName("Ramzes")
						.withLastName("Faraon")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withPhoneNumber("987124098")
						.build())
				.withAddress(sampleAddress)
				.withPosition(samplePosition)
				.withAgency(sampleAgency)
				.build();
		List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
		employees.add(employee1);
		employees.add(employee2);	
		//when
		
		List<Long>result = employeeMapper.onIds(employees);
		//then
		assertEquals(2,result.size());
		assertEquals(employees.get(0).getId(),result.get(0));
		assertEquals(employees.get(1).getId(),result.get(1));
	}
	
}
