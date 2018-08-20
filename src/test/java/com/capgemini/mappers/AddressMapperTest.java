package com.capgemini.mappers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.AddressEmbedded;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.AddressTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTest {
	
	@Autowired
	AddressMapper addressMapper;
	
	@Test
	public void testShouldMapOnSingleTo() throws MandatoryValueNotFilledException{
		//given
		AddressEmbedded address = AddressEmbedded.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();
		//when
		AddressTO result= addressMapper.onTO(address);
		//then
		assertEquals(address.getCity(),result.getCity());
		assertEquals(address.getStreet(),result.getStreet());
		assertEquals(address.getZipcode(),result.getZipcode());
		assertEquals(address.getLocal(),result.getLocal());
		
	}
	
	@Test
	public void testShouldMapOnSingleEntity() throws MandatoryValueNotFilledException{
		//given
		AddressTO address = AddressTO.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();
		//when
		AddressEmbedded result= addressMapper.onEntity(address);
		//then
		assertEquals(address.getCity(),result.getCity());
		assertEquals(address.getStreet(),result.getStreet());
		assertEquals(address.getZipcode(),result.getZipcode());
		assertEquals(address.getLocal(),result.getLocal());
		
	}
	
}
