package com.capgemini.dao.impl;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CustomerDao;
import com.capgemini.domain.CustomerEntity;

@Repository
public class CustomerDaoImpl extends AbstractDao< CustomerEntity,Long> implements CustomerDao {

}
