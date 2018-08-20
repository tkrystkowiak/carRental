package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.AgencyDao;
import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.exceptions.MandatoryValueNotFilledException;
import com.capgemini.mappers.AgencyMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.AgencyService;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

@Service
public class AgencyServiceImpl implements AgencyService {

	private final AgencyDao agencyDao;
	private final EmployeeDao employeeDao;
	private final CarDao carDao;
	private final EmployeeMapper employeeMapper;
	private final AgencyMapper agencyMapper;

	@Autowired
	public AgencyServiceImpl(AgencyDao agencyDao, EmployeeDao employeeDao, CarDao carDao, EmployeeMapper employeeMapper,
			AgencyMapper agencyMapper) {
		this.agencyDao = agencyDao;
		this.employeeDao = employeeDao;
		this.carDao = carDao;
		this.employeeMapper = employeeMapper;
		this.agencyMapper = agencyMapper;
	}

	@Override
	@Transactional(readOnly = false)
	public void addAgency(AgencyTO agency) throws MandatoryValueNotFilledException {
		agencyDao.save(agencyMapper.onEntity(agency));
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteAgency(Long id) {
		agencyDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateAgency(AgencyTO agency) throws MandatoryValueNotFilledException {
		agencyDao.update(agencyMapper.onEntity(agency));
	}

	@Override
	@Transactional(readOnly = false)
	public void addEmployeeToAgency(Long employeeId, Long agencyId) throws MandatoryValueNotFilledException {
		EmployeeTO empToUpdate = employeeMapper.onTO(employeeDao.findOne(employeeId));
		AgencyTO agency = agencyMapper.onTO(agencyDao.findOne(agencyId));
		empToUpdate.setAgency(agency);
		employeeDao.update(employeeMapper.onEntity(empToUpdate));
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEmployeeFromAgency(Long employeeId, Long agencyId) throws MandatoryValueNotFilledException {
		EmployeeTO empToUpdate = employeeMapper.onTO(employeeDao.findOne(employeeId));
		if (empToUpdate.getAgency().getId() == agencyId) {
			empToUpdate.setAgency(null);
		}
		employeeDao.update(employeeMapper.onEntity(empToUpdate));
	}

	@Override
	public List<EmployeeTO> findCurrentAgencyEmployees(Long id) throws MandatoryValueNotFilledException {

		return employeeMapper.onTOs(employeeDao.findEmployeesByAgency(id));
		
	}

	@Override
	public List<EmployeeTO> findCurrentAgencyEmployeesAssignedToCar(Long carId, Long agencyId)
			throws MandatoryValueNotFilledException {
		List<Long> employeesAssignedToAgency = employeeMapper.onIds((employeeDao.findEmployeesByAgency(agencyId)));
		List<Long> employeesAssignedToCar = employeeMapper.onIds(carDao.findOne(carId).getListOfGuardians());

		employeesAssignedToAgency.retainAll(employeesAssignedToCar);
		List<EmployeeTO> toReturn = new ArrayList<EmployeeTO>();
		for (Long id : employeesAssignedToAgency) {
			toReturn.add(employeeMapper.onTO(employeeDao.findOne(id)));
		}

		return toReturn;
	}

}
