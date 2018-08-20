package com.capgemini.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.RentalDaoImpl;
import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CustomerEntity;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.domain.RentalEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RentalDaoTest {
	
	@Autowired
	RentalDaoImpl rentalDao;
	
	@Test
	public void shouldCountNumberOfRentedCars() throws MandatoryValueNotFilledException, ParseException{
		//given
		RentalEntity first = RentalEntity.newBuilder()
				.withCar(getSampleCar("black","BMW","Sedan"))
				.withRentAgencyId(getSampleAgency())
				.withReturnAgencyId(getSampleAgency())
				.withRentDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-13").getTime()))
				.withReturnDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-23").getTime()))
				.withCharge(100)
				.withCustomer(getSampleCustomer())
				.build();
		
		RentalEntity second = RentalEntity.newBuilder()
				.withCar(getSampleCar("red","Ferrari","Sport"))
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
		Date startDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-15").getTime());
		Date endDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-20").getTime());
		//when
		Long nuberOfCars = rentalDao.countCarsRentedInTimePeriod(startDate, endDate);
		Long expected = 2L;
		assertEquals(expected,nuberOfCars);
		
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
				List<Long> cars = rentalDao.countNumberOfDistinctRenters();
				Long expected = 2L;
				//then
				assertTrue(cars.isEmpty());
				
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
	
}
