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

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.RentalDao;
import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.domain.PositionEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.impl.CarServiceImpl;
import com.capgemini.types.AddressTO;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PersonalDataTO;
import com.capgemini.types.PositionTO;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceUnitTest {

	@InjectMocks
	CarServiceImpl carService;
	
	@Mock
	CarDao carDao;
	
	@Mock
	EmployeeDao employeeDao;
	
	@Mock
	RentalDao rentalDao;
	
	@Mock
	EmployeeMapper employeeMapper;
	
	@Mock
	CarMapper carMapper;
	
	
	@Test
	public void shouldAssignGuardianToCar() throws MandatoryValueNotFilledException, ParseException{
		
		EmployeeEntity sampleEmployee = getSampleEmployee();
		EmployeeTO sampleEmployeeTO = getSampleEmployeeTO();
		CarEntity sampleCar = getSampleCar();
		CarTO sampleCarTO = getSampleCarTO();
		
		when(employeeDao.findOne(1L)).thenReturn(sampleEmployee);
		when(employeeMapper.onTO(sampleEmployee)).thenReturn(sampleEmployeeTO);
		when(carDao.findOne(1L)).thenReturn(sampleCar);
		when(carMapper.onTO(sampleCar)).thenReturn(sampleCarTO);
		List<EmployeeEntity> guardians =  new ArrayList<EmployeeEntity>();
		guardians.add(sampleEmployee);
		List<EmployeeTO> guardiansTO =  new ArrayList<EmployeeTO>();
		guardiansTO.add(sampleEmployeeTO);
		sampleCarTO.setListOfGuardians(guardiansTO);
		sampleCar.setListOfGuardians(guardians);
		
		when(carMapper.onEntity(sampleCarTO)).thenReturn(sampleCar);
		carService.assignToGuardian(1L, 1L);
	}
	
	@Test
	public void shouldFindByTypeAndBrand() throws MandatoryValueNotFilledException{
		//given
		List<CarEntity> cars = new ArrayList<CarEntity>();
		cars.add(getSampleCar());
		List<CarTO> carsTO = new ArrayList<CarTO>();
		carsTO.add(getSampleCarTO());
		when(carDao.findByTypeAndBrand("sedan", "tesla")).thenReturn(cars);
		when(carMapper.onTOs(cars)).thenReturn(carsTO);
		//when
		List<CarTO> result = carService.findByTypeAndBrand("sedan", "tesla");
		//then
		assertEquals(carsTO,result);	
	}
	
	@Test
	public void shouldFindByGuardian() throws MandatoryValueNotFilledException{
		//given
		List<CarEntity> cars = new ArrayList<CarEntity>();
		cars.add(getSampleCar());
		List<CarTO> carsTO = new ArrayList<CarTO>();
		carsTO.add(getSampleCarTO());
		when(carDao.findByGuardian(1L)).thenReturn(cars);
		when(carMapper.onTOs(cars)).thenReturn(carsTO);
		//when
		List<CarTO> result = carService.findByGuardian(1L);
		//then
		assertEquals(carsTO,result);	
	}
	
	@Test
	public void shouldReturnNumberOfRentedCars() throws ParseException{
		//given
		Date startDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-15").getTime());
		Date endDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-20").getTime());
		Long expected = 13L;
		when(rentalDao.countCarsRentedInTimePeriod(startDate, endDate)).thenReturn(expected);
		//when
		Long result = carService.countCarsRentedInPeriod(startDate, endDate);
		//then
		assertEquals(expected,result);
	}
	
	@Test
	public void ShouldFindCarsWithNumberOfRentersBiggerThan10() throws MandatoryValueNotFilledException{
		//given
		long numberOfRenters = 10L;
		List<CarEntity> cars = new ArrayList<CarEntity>();
		cars.add(getSampleCar());
		List<CarTO> carsTO = new ArrayList<CarTO>();
		carsTO.add(getSampleCarTO());
		when(carDao.findCarsWithGivenNumberOfRenters(numberOfRenters)).thenReturn(cars);
		when(carMapper.onTOs(cars)).thenReturn(carsTO);
		//when
		List<CarTO> result = carService.findCarsWithMoreDistinctRentersThan(numberOfRenters);
		//then
		assertEquals(carsTO,result);
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
	
	
	
	private CarTO getSampleCarTO() throws MandatoryValueNotFilledException{
		return CarTO.newBuilder()
				.withType("Sedan")
				.withBrand("Tesla")
				.withColor("Red")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
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
		return new AgencyTO(getSampleAddressTO(),"141543789");
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
	
	private AddressEmbedded getSampleAddress() throws MandatoryValueNotFilledException{
		return AddressEmbedded.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();
	}
	
	private AgencyEntity getSampleAgency() throws MandatoryValueNotFilledException{
		return new AgencyEntity(getSampleAddress(),"141543789");
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
	
}
