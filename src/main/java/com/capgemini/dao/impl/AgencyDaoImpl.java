package com.capgemini.dao.impl;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.AgencyDao;
import com.capgemini.domain.AgencyEntity;

@Repository
public class AgencyDaoImpl extends AbstractDao<AgencyEntity, Long> implements AgencyDao{}
