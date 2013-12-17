package org.telosys.starterkits.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telosys.starterkits.bean.Employee;
import org.telosys.starterkits.dao.jpa.EmployeeDao;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplUnitTest {
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	@Mock
	private EmployeeDao employeeDao;
	
	@Test
	public void load() {
		// Given
		String code = "test";

		Employee employee = new Employee();
		employee.setCode(code);
		
		when(employeeDao.load(code)).thenReturn(employee);

		// When
		Employee employeeResult = employeeService.load(code);
		
		// Then
		assertEquals(code, employeeResult.getCode());
	}
	
	@Test
	public void save() {
		// Given
		Employee employeeToSave = new Employee();
		Employee employeeSaved = new Employee();
		
		when(employeeDao.save(employeeToSave)).thenReturn(employeeSaved);

		// When
		Employee employeeResult = employeeService.save(employeeToSave);
		
		// Then
		assertTrue(employeeResult != employeeToSave);
		assertTrue(employeeResult == employeeSaved);
	}

	@Test
	public void delete() {
		// Given
		String code = "test";
		
		// When
		employeeService.delete(code);
		
		// Then
		verify(employeeDao).delete(code);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee());
		
		when(employeeDao.search(criteria)).thenReturn(employees);
		
		// When
		List<Employee> employeesResult = employeeService.search(criteria);
		
		// Then
		assertTrue(employeesResult == employees);
	}

	@Test
	public void loadAll() {
		// Given
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee());
		
		when(employeeDao.loadAll()).thenReturn(employees);
		
		// When
		List<Employee> employeesResult = employeeService.loadAll();
		
		// Then
		assertTrue(employeesResult == employees);
	}
	
}
