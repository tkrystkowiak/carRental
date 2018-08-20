package com.capgemini.mappers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddressMapperTest.class, AgencyMapperTest.class, CarMapperTest.class, EmployeeMapperTest.class,
		PersonalDataMapperTest.class, PositionMapperTest.class })
public class MapperTests {

}
