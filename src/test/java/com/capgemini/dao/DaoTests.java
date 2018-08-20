package com.capgemini.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AgencyDaoTest.class,CarDaoTest.class,EmployeeDaoTest.class,RentalDaoTest.class})
public class DaoTests {

}
