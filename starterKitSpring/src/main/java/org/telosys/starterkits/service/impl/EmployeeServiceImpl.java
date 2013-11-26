package org.telosys.starterkits.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Employee;
import org.telosys.starterkits.dao.jpa.EmployeeDao;
import org.telosys.starterkits.service.EmployeeService;

/**
 * Service : Employee.
 */
@Component
public class EmployeeServiceImpl implements EmployeeService {
		
	@Resource
	private EmployeeDao employeeDao;
	
	public Employee load(final String code) {
		return employeeDao.load(code);
	}
	
	public Employee save(final Employee employee) {
		return employeeDao.save(employee);
	}

	public void delete(final String code) {
		employeeDao.delete(code);
	}

	public List<Employee> search(final Map<String,Object> criteria) {
		return employeeDao.search(criteria);
	}

	public List<Employee> loadAll() {
		return employeeDao.loadAll();
	}
	
}
