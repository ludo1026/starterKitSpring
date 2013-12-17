package org.telosys.starterkits.dao.jpa.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telosys.starterkits.bean.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDaoUnitTest {

	@InjectMocks
	private EmployeeDaoImpl employeeDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		String code = "test";

		Employee employee = new Employee();
		employee.setCode(code);

		when(entityManager.find(Employee.class, code)).thenReturn(employee);

		// When
		Employee employeeResult = employeeDao.load(code);

		// Then
		assertEquals(code, employeeResult.getCode());
	}

	@Test
	public void save() {
		// Given
		Employee employeeToSave = new Employee();
		Employee employeeSaved = new Employee();

		when(entityManager.merge(employeeToSave)).thenReturn(employeeSaved);

		// When
		Employee employeeResult = employeeDao.save(employeeToSave);

		// Then
		assertTrue(employeeResult != employeeToSave);
		assertTrue(employeeResult == employeeSaved);
	}

	@Test
	public void delete() {
		// Given
		String code = "test";

		Employee employee = new Employee();
		when(entityManager.find(Employee.class, code)).thenReturn(employee);

		// When
		employeeDao.delete(code);

		// Then
		verify(entityManager).remove(employee);
	}

	@Test
	public void loadAll() {
		// Given
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Employee.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(employees);

		// When
		List<Employee> employeesResult = employeeDao.loadAll();

		// Then
		assertTrue(employeesResult == employees);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Employee.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(employees);

		// When
		List<Employee> employeesResult = employeeDao.search(null);

		// Then
		assertTrue(employeesResult == employees);
	}

}
