package org.telosys.starterkits.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;
import org.telosys.starterkits.dao.jpa.EmployeeGroupDao;
import org.telosys.starterkits.dao.repository.EmployeeGroupRepository;
import org.telosys.starterkits.service.EmployeeGroupService;

/**
 * Service : EmployeeGroup.
 */
@Component
@Transactional
public class EmployeeGroupServiceImpl implements EmployeeGroupService {
		
	@Resource
	private EmployeeGroupDao employeegroupDao;
	@Resource
	private EmployeeGroupRepository employeegroupRepository;
	
	public EmployeeGroup load(final EmployeeGroupId id) {
		return employeegroupDao.load(id);
	}
	
	public EmployeeGroup save(final EmployeeGroup employeegroup) {
		return employeegroupDao.save(employeegroup);
	}

	public void delete(final EmployeeGroupId id) {
		employeegroupDao.delete(id);
	}

	public List<EmployeeGroup> search(final Map<String,Object> criteria) {
		return employeegroupDao.search(criteria);
	}

	public List<EmployeeGroup> loadAll() {
		return employeegroupDao.loadAll();
	}
	
}
