package com.capgemini.mappers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.AddressTO;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.CarTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgencyMapperTest {
	
	@Autowired
	AgencyMapper agencyMapper;
	
	@Test
	public void shouldMapSingleEntityOnTo() throws MandatoryValueNotFilledException{
		//given
		AddressEmbedded address = AddressEmbedded.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();
		AgencyEntity sampleAgency = new AgencyEntity(address,"141543789");
		sampleAgency.setId(1L);
		//when
		AgencyTO result = agencyMapper.onTO(sampleAgency);
		//then
		assertEquals(sampleAgency.getAddress().getCity(),result.getAddress().getCity());
		assertEquals(sampleAgency.getId(),result.getId());
		assertEquals(sampleAgency.getPhoneNumber(),result.getPhoneNumber());
		
	}
	
	@Test
	public void shouldMapSingleTOOnEntity() throws MandatoryValueNotFilledException{
		//given
		AddressTO address = AddressTO.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();
		AgencyTO sampleAgency = new AgencyTO(address,"141543789");
		sampleAgency.setId(1L);
		//when
		AgencyEntity result = agencyMapper.onEntity(sampleAgency);
		//then
		assertEquals(sampleAgency.getAddress().getCity(),result.getAddress().getCity());
		assertEquals(sampleAgency.getId(),result.getId());
		assertEquals(sampleAgency.getPhoneNumber(),result.getPhoneNumber());
		
	}
}
