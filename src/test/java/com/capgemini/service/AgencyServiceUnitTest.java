package com.capgemini.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.dao.AgencyDao;
import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.domain.PositionEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.mappers.AgencyMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.impl.AgencyServiceImpl;
import com.capgemini.types.AddressTO;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PersonalDataTO;
import com.capgemini.types.PositionTO;

@RunWith(MockitoJUnitRunner.class)
public class AgencyServiceUnitTest {

	@InjectMocks
	AgencyServiceImpl agencyService;
	
	@Mock
	AgencyDao agencyDao;
	
	@Mock
	EmployeeDao employeeDao;
	
	@Mock
	CarDao carDao;
	
	@Mock
	AgencyMapper agencyMapper;
	
	@Mock
	EmployeeMapper employeeMapper;
	
	@Test
	public void shouldSaveAgency() throws MandatoryValueNotFilledException{
		AgencyTO sampleAgencyTo = getSampleAgencyTO();
		AgencyEntity sampleAgency = getSampleAgency();
		when(agencyMapper.onEntity(sampleAgencyTo)).thenReturn(sampleAgency);
		agencyService.addAgency(sampleAgencyTo);
	}
	
	@Test
	public void shouldDeleteAgency() throws MandatoryValueNotFilledException{
		agencyService.deleteAgency(1L);
	}
	
	@Test
	public void shouldUpdateAgency() throws MandatoryValueNotFilledException{
		agencyService.updateAgency(getSampleAgencyTO());
	}
	
	@Test
	public void shouldAddEmployeeToAgency() throws MandatoryValueNotFilledException, ParseException{
		Long employeeId = 1L;
		Long agencyId = 1L;
		EmployeeEntity sampleEmployee = getSampleEmployee();
		EmployeeTO sampleEmployeeTo = getSampleEmployeeTO();
		AgencyEntity sampleAgency = getSampleAgency();
		AgencyTO sampleAgencyTO = getSampleAgencyTO();
		
		when(employeeDao.findOne(employeeId)).thenReturn(sampleEmployee);
		when(employeeMapper.onTO(sampleEmployee)).thenReturn(sampleEmployeeTo);
		when(agencyDao.findOne(agencyId)).thenReturn(sampleAgency);
		when(agencyMapper.onTO(sampleAgency)).thenReturn(sampleAgencyTO);
		when(employeeMapper.onEntity(sampleEmployeeTo)).thenReturn(sampleEmployee);
		
		agencyService.addEmployeeToAgency(employeeId, agencyId);
	}
	
	@Test
	public void shouldDeleteEmployeeToAgency() throws MandatoryValueNotFilledException, ParseException{
		Long employeeId = 1L;
		Long agencyId = 1L;
		EmployeeEntity sampleEmployee = getSampleEmployee();
		EmployeeTO sampleEmployeeTo = getSampleEmployeeTO();
		
		when(employeeDao.findOne(employeeId)).thenReturn(sampleEmployee);
		when(employeeMapper.onTO(sampleEmployee)).thenReturn(sampleEmployeeTo);
		when(employeeMapper.onEntity(sampleEmployeeTo)).thenReturn(sampleEmployee);
		
		agencyService.deleteEmployeeFromAgency(employeeId, agencyId);
	}
	
	@Test
	public void shouldFindCurrentAgencyEmployees() throws MandatoryValueNotFilledException, ParseException{
		//given
		Long id = 1L;
		List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
		List<EmployeeTO> employeeTOs = new ArrayList<EmployeeTO>();
		when(employeeDao.findEmployeesByAgency(id)).thenReturn(employees);
		when(employeeMapper.onTOs(employees)).thenReturn(employeeTOs);
		//when
		List<EmployeeTO> result =agencyService.findCurrentAgencyEmployees(id);
		//then
		assertEquals(employeeTOs,result);
		
	}
	
	@Test
	public void shouldFindCurrentAgencyEmployeesAssingedToACar() throws MandatoryValueNotFilledException, ParseException{
		//given
		Long carId = 1L;
		Long agencyId = 1L;
		Long employeeId = 1L;
		List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
		List<Long> employeeIds = new ArrayList<Long>();
		employeeIds.add(employeeId);
		CarEntity car = getSampleCar();
		EmployeeEntity employee = getSampleEmployee();
		EmployeeTO employeeTo = getSampleEmployeeTO();
		when(employeeDao.findEmployeesByAgency(agencyId)).thenReturn(employees);
		when(employeeMapper.onIds(employees)).thenReturn(employeeIds);
		when(carDao.findOne(carId)).thenReturn(car);
		when(employeeMapper.onIds(null)).thenReturn(employeeIds);
		when(employeeDao.findOne(employeeId)).thenReturn(employee);
		when(employeeMapper.onTO(employee)).thenReturn(employeeTo);
		//when
		List<EmployeeTO> result = agencyService.findCurrentAgencyEmployeesAssignedToCar(carId, agencyId);
		//then
		assertEquals(1,result.size());
		assertEquals(employeeTo,result.get(0));
	}
	
	private AddressEmbedded getSampleAddress() throws MandatoryValueNotFilledException{
		return AddressEmbedded.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();
	}
	
	private AgencyEntity getSampleAgency() throws MandatoryValueNotFilledException{
		AgencyEntity sample = new AgencyEntity(getSampleAddress(),"141543789");
		sample.setId(1L);
		return sample;
	}
	
	private AddressTO getSampleAddressTO() throws MandatoryValueNotFilledException{
		return AddressTO.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();
	}
	
	private AgencyTO getSampleAgencyTO() throws MandatoryValueNotFilledException{
		AgencyTO sample = new AgencyTO(getSampleAddressTO(),"141543789");
		sample.setId(1L);
		return sample;
	}
	
	private EmployeeTO getSampleEmployeeTO() throws MandatoryValueNotFilledException, ParseException{
		return EmployeeTO.newBuilder()
				.withPersonalData(PersonalDataTO.newBuilder()
						.withFirstName("Albert")
						.withLastName("Einstein")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withPhoneNumber("987124098")
						.build())
				.withAddress(getSampleAddressTO())
				.withPosition(new PositionTO(1L, "developer"))
				.withAgency(getSampleAgencyTO())
				.build();
	}
	
	private EmployeeEntity getSampleEmployee() throws MandatoryValueNotFilledException, ParseException{
		return EmployeeEntity.newBuilder()
				.withPersonalData(PersonalDataEmbedded.newBuilder()
						.withFirstName("Albert")
						.withLastName("Einstein")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1989-02-28").getTime()))
						.withPhoneNumber("987124098")
						.build())
				.withAddress(getSampleAddress())
				.withPosition(new PositionEntity("developer"))
				.withAgency(getSampleAgency())
				.build();
	}
	
	private CarEntity getSampleCar() throws MandatoryValueNotFilledException{
		return CarEntity.newBuilder()
				.withType("Sedan")
				.withBrand("Tesla")
				.withColor("Red")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
	}
	
}
