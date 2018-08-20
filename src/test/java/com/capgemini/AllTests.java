package com.capgemini;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.dao.DaoTests;
import com.capgemini.mappers.MapperTests;
import com.capgemini.service.ServiceTests;

@RunWith(Suite.class)
@SuiteClasses({DaoTests.class,MapperTests.class,ServiceTests.class})
public class AllTests {

}
