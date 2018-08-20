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

import com.capgemini.domain.PersonalDataEmbedded;
import com.capgemini.domain.PositionEntity;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.types.PersonalDataTO;
import com.capgemini.types.PositionTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionMapperTest {

	@Autowired
	PositionMapper positionMapper;

	@Test
	public void testShouldMapOnSingleTo() {
		// given
		PositionEntity samplePosition = new PositionEntity("developer");
		samplePosition.setId(1L);
		// when
		PositionTO result = positionMapper.onTO(samplePosition);
		// then
		assertEquals(samplePosition.getId(), result.getId());
		assertEquals(samplePosition.getTitle(), result.getTitle());

	}

	@Test
	public void testShouldMapOnSingleEntity() throws MandatoryValueNotFilledException, ParseException {
		// given
		PositionTO samplePosition = new PositionTO(1L,"developer");
		// when
		PositionEntity result = positionMapper.onEntity(samplePosition);
		// then
		assertEquals(samplePosition.getId(), result.getId());
		assertEquals(samplePosition.getTitle(), result.getTitle());
	}

}
