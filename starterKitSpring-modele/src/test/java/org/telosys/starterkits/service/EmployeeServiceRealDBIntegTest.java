package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Employee;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class EmployeeServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private EmployeeService employeeService;

	@Test
	public void cycle_vie_complet() {

		String code = "1";

		Employee employee = new Employee();
		employee.setCode(code);
		//employee.setName("Test " + code);

		// Create
		this.employeeService.save(employee);

		// Search
		employee = this.employeeService.load(code);
		Assert.assertNotNull(employee);

		// Update
		//employee.setName("Test 2 " + code);
		employee = this.employeeService.save(employee);

		// Search
		employee = this.employeeService.load(code);
		//Assert.assertEquals("Test 2 " + code, employee.getName());

		// Delete
		this.employeeService.delete(employee.getCode());

		// Search
		employee = this.employeeService.load(code);
		Assert.assertNull(employee);
	}

}
