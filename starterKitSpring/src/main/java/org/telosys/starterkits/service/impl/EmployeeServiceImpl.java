package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Employee;
import org.telosys.starterkits.dao.jpa.EmployeeDao;
import org.telosys.starterkits.dao.repository.EmployeeRepository;
import org.telosys.starterkits.service.EmployeeService;

/**
 * Service : Employee.
 */
@Component
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
		
	@Resource
	private EmployeeDao employeeDao;
	@Resource
	private EmployeeRepository employeeRepository;
	
	public Employee load(final String code) {
		return employeeRepository.findOne(code);
	}
	
	public Employee save(final Employee employee) {
		return employeeRepository.save(employee);
	}

	public void delete(final String code) {
		employeeRepository.delete(code);
	}

	public List<Employee> search(final Map<String,Object> criteria) {
		return employeeDao.search(criteria);
	}

	public List<Employee> loadAll() {
		List<Employee> employees = new ArrayList<Employee>();
		for (Employee employee : employeeRepository.findAll()) {
			employees.add(employee);
		}
		return employees;
	}
	
	@Transactional(readOnly=true)
	public Page<Employee> findAllByPage(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
	
}
