package com.capgemini.dao;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.AgencyDaoImpl;
import com.capgemini.domain.AddressEmbedded;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.MandatoryValueNotFilledException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AgencyDaoTest {

	@Autowired
	AgencyDaoImpl agencyDao;
	
	@Test
	public void testShouldSaveAgency() throws MandatoryValueNotFilledException{
		AddressEmbedded sampleAddress = AddressEmbedded.newBuilder()
				.withCity("Warszawa")
				.withStreet("Krajewskiego")
				.withZipcode("01-520")
				.withLocal("2")
				.build();
		
		AgencyEntity sampleAgency = new AgencyEntity(sampleAddress,"141543789");
		agencyDao.save(sampleAgency);
	}
	
	
	
}
