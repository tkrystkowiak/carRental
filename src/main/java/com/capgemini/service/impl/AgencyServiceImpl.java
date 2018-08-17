package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.dao.AgencyDao;
import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.MandatoryValueNotFilledException;
import com.capgemini.mappers.AgencyMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.AgencyService;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

public class AgencyServiceImpl implements AgencyService {

	private final AgencyDao agencyDao;
	private final EmployeeDao employeeDao;
	private final CarDao carDao;
	
	@Autowired
	public AgencyServiceImpl(AgencyDao agencyDao,EmployeeDao employeeDao, CarDao carDao) {
		this.agencyDao = agencyDao;
		this.employeeDao = employeeDao;
		this.carDao = carDao;
	}

	@Override
	public void addAgency(AgencyTO agency) {
		agencyDao.save(AgencyMapper.onEntity(agency));
	}

	@Override
	public void deleteAgency(Long id) {
		agencyDao.delete(id);
	}

	@Override
	public void updateAgency(AgencyTO agency) {
		agencyDao.update(AgencyMapper.onEntity(agency));
	}

	@Override
	public void addEmployeeToAgency(Long employeeId, Long agencyId) throws MandatoryValueNotFilledException {
		EmployeeTO empToUpdate = EmployeeMapper.onTO(employeeDao.findOne(employeeId));
		AgencyTO agency = AgencyMapper.onTO(agencyDao.findOne(agencyId));
		empToUpdate.setAgency(agency);
		employeeDao.update(EmployeeMapper.onEntity(empToUpdate));
	}

	@Override
	public void deleteEmployeeFromAgency(Long employeeId, Long agencyId) throws MandatoryValueNotFilledException {
		EmployeeTO empToUpdate = EmployeeMapper.onTO(employeeDao.findOne(employeeId));
		if(empToUpdate.getAgency().getId()==agencyId){
			empToUpdate.setAgency(null);
		}
	}

	@Override
	public List<EmployeeTO> findCurrentAgencyEmployees(Long id) throws MandatoryValueNotFilledException {
		
		return EmployeeMapper.onTOs(employeeDao.findEmployeesByAgency(id));
	}

	@Override
	public List<EmployeeTO> findCurrentAgencyEmployeesAssignedToCar(Long carId, Long agencyId) throws MandatoryValueNotFilledException {
		List<Long> employeesAssignedToAgency = EmployeeMapper.onIds((employeeDao.findEmployeesByAgency(agencyId)));
		List<Long> employeesAssignedToCar = EmployeeMapper.onIds(carDao.findOne(carId).getListOfGuardians());
		
		employeesAssignedToAgency.retainAll(employeesAssignedToCar);
		List<EmployeeTO> toReturn = new ArrayList<EmployeeTO>();
		for(Long id: employeesAssignedToAgency){
			toReturn.add(EmployeeMapper.onTO(employeeDao.findOne(id)));
		}
		
		return toReturn;
	}

}
