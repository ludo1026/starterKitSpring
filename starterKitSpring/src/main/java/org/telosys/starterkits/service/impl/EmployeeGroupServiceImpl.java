package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return employeegroupRepository.findOne(id);
	}
	
	public EmployeeGroup save(final EmployeeGroup employeegroup) {
		return employeegroupRepository.save(employeegroup);
	}

	public void delete(final EmployeeGroupId id) {
		employeegroupRepository.delete(id);
	}

	public List<EmployeeGroup> search(final Map<String,Object> criteria) {
		return employeegroupDao.search(criteria);
	}

	public List<EmployeeGroup> loadAll() {
		List<EmployeeGroup> employeegroups = new ArrayList<EmployeeGroup>();
		for (EmployeeGroup employeegroup : employeegroupRepository.findAll()) {
			employeegroups.add(employeegroup);
		}
		return employeegroups;
	}
	
	@Transactional(readOnly=true)
	public Page<EmployeeGroup> findAllByPage(Pageable pageable) {
		return employeegroupRepository.findAll(pageable);
	}
	
}
