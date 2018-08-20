package com.capgemini.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.dao.RentalDaoTest;

@RunWith(Suite.class)
@SuiteClasses({AgencyServiceUnitTest.class,CarServiceIntegrationTest.class,EmployeeServiceUnitTest.class,CarServiceUnitTest.class})
public class ServiceTests {

}
