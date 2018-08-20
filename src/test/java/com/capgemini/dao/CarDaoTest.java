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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.AgencyDaoImpl;
import com.capgemini.dao.impl.CarDaoImpl;
import com.capgemini.dao.impl.CustomerDaoImpl;
import com.capgemini.dao.impl.EmployeeDaoImpl;
import com.capgemini.dao.impl.RentalDaoImpl;
import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CustomerEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.domain.PositionEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class CarDaoTest {
	
	@PersistenceContext
	EntityManager enitityManager;
	
	@Autowired
	CarDaoImpl carDao;
	
	@Autowired
	EmployeeDaoImpl employeeDao;
	
	@Autowired
	CustomerDaoImpl customerDao;
	
	@Autowired
	RentalDaoImpl rentalDao;
	
	@Autowired
	AgencyDaoImpl agencyDao;
	
	@Test
	public void testShouldHaveOneCarInMemory() throws MandatoryValueNotFilledException {
		carDao.save(getSampleCar("red", "Tesla", "Sedan"));
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
		
		
		List<EmployeeEntity> listOfGuardians = new ArrayList<EmployeeEntity>();
		listOfGuardians.add(getSampleEmployee());
		
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
		CarEntity savedCar = carDao.save(car);
		
		Long guardianId = savedCar.getListOfGuardians().get(0).getId();
		assertEquals(car.getId(),carDao.findByGuardian(guardianId).get(0).getId());
	}
	
	@Test
	public void testShouldFindCarByBrandAndType() throws MandatoryValueNotFilledException {
		carDao.save(getSampleCar("black", "BMW", "Sedan"));
		List<CarEntity> carList = carDao.findByTypeAndBrand("Sedan", "BMW");
		assertEquals("BMW",carList.get(0).getBrand());
	}
	
	@Test
	public void shouldFindDistinctCustomers() throws MandatoryValueNotFilledException, ParseException{
		//given
		CarEntity sampleCar1 = getSampleCar("black","BMW","Sedan");
		
				RentalEntity first = RentalEntity.newBuilder()
						.withCar(sampleCar1)
						.withRentAgencyId(getSampleAgency())
						.withReturnAgencyId(getSampleAgency())
						.withRentDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-13").getTime()))
						.withReturnDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-23").getTime()))
						.withCharge(100)
						.withCustomer(getSampleCustomer())
						.build();
				
				RentalEntity second = RentalEntity.newBuilder()
						.withCar(sampleCar1)
						.withRentAgencyId(getSampleAgency())
						.withReturnAgencyId(getSampleAgency())
						.withRentDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-10").getTime()))
						.withReturnDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-24").getTime()))
						.withCharge(100)
						.withCustomer(getSampleCustomer())
						.build();
				
				RentalEntity third = RentalEntity.newBuilder()
						.withCar(getSampleCar("silver","Skoda","Combi"))
						.withRentAgencyId(getSampleAgency())
						.withReturnAgencyId(getSampleAgency())
						.withRentDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-16").getTime()))
						.withReturnDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-24").getTime()))
						.withCharge(100)
						.withCustomer(getSampleCustomer())
						.build();
				
				rentalDao.save(first);
				rentalDao.save(second);
				rentalDao.save(third);
				
				//when
				List<CarEntity> cars = carDao.findCarsWithGivenNumberOfRenters(0L);
				//then
				assertEquals(2,cars.size());
	}
	
	@Test
	public void testShouldDeleteRentalWhenDeletingCarButNotCustomerAndAgency() throws MandatoryValueNotFilledException, ParseException {
		
		CarEntity sampleCar = CarEntity.newBuilder()
				.withType("Sedan")
				.withBrand("Tesla")
				.withColor("red")
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
				.build();
		
		RentalEntity first = RentalEntity.newBuilder()
				.withRentAgencyId(getSampleAgency())
				.withReturnAgencyId(getSampleAgency())
				.withRentDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-13").getTime()))
				.withReturnDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-23").getTime()))
				.withCharge(100)
				.withCustomer(getSampleCustomer())
				.withCar(sampleCar)
				.build();
		
		RentalEntity second = RentalEntity.newBuilder()
				.withRentAgencyId(getSampleAgency())
				.withReturnAgencyId(getSampleAgency())
				.withRentDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-10").getTime()))
				.withReturnDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-24").getTime()))
				.withCharge(100)
				.withCustomer(getSampleCustomer())
				.withCar(sampleCar)
				.build();
		
		RentalEntity third = RentalEntity.newBuilder()
				.withRentAgencyId(getSampleAgency())
				.withReturnAgencyId(getSampleAgency())
				.withRentDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-16").getTime()))
				.withReturnDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-24").getTime()))
				.withCharge(100)
				.withCustomer(getSampleCustomer())
				.withCar(sampleCar)
				.build();
		List<RentalEntity> rentals = new ArrayList<RentalEntity>();
		rentals.add(first);
		rentals.add(second);
		rentals.add(third);
		sampleCar.setRentals(rentals);
		Long saveId = carDao.save(sampleCar).getId();
		assertEquals(3,rentalDao.findAll().size());
		assertEquals(3,customerDao.findAll().size());
		assertEquals(6,agencyDao.findAll().size());
		carDao.delete(saveId);
		assertEquals(0,rentalDao.findAll().size());
		assertEquals(3,customerDao.findAll().size());
		assertEquals(6,agencyDao.findAll().size());
		
	}
	
	private CarEntity getSampleCar(String color, String brand, String type) throws MandatoryValueNotFilledException{
		return CarEntity.newBuilder()
				.withType(type)
				.withBrand(brand)
				.withColor(color)
				.withEngineCapacity(3000)
				.withMileage(50000)
				.withHorsePower(234)
				.withYearOfProduction(2012)
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
	
	private CustomerEntity getSampleCustomer() throws MandatoryValueNotFilledException, ParseException{
		return CustomerEntity.newBuilder()
				.withAddres(getSampleAddress())
				.withCreditCardNumber("8902899943829234")
				.withEmail("dgrabowski@wp.pl")
				.withPersonalData(PersonalDataEmbedded.newBuilder()
						.withFirstName("Dobrogost")
						.withLastName("Grabowski")
						.withBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1969-06-20").getTime()))
						.withPhoneNumber("645097456")
						.build())
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
	
	
}
