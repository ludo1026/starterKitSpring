package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;

import org.telosys.starterkits.test.common.AbstractMemoryDBTest;

public class EmployeeGroupServiceMemoryDBIntegTest extends AbstractMemoryDBTest {

	@Override
	protected String getReferentielDataFilename() {
		return null;
	}

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private EmployeeGroupService employeegroupService;

	@Test
	public void cycle_vie_complet() {

		EmployeeGroupId id = new EmployeeGroupId();

		EmployeeGroup employeegroup = new EmployeeGroup();
		employeegroup.setId(id);
		//employeegroup.setName("Test " + id);

		// Create
		this.employeegroupService.save(employeegroup);

		// Search
		employeegroup = this.employeegroupService.load(id);
		Assert.assertNotNull(employeegroup);

		// Update
		//employeegroup.setName("Test 2 " + id);
		employeegroup = this.employeegroupService.save(employeegroup);

		// Search
		employeegroup = this.employeegroupService.load(id);
		//Assert.assertEquals("Test 2 " + id, employeegroup.getName());

		// Delete
		this.employeegroupService.delete(employeegroup.getId());

		// Search
		employeegroup = this.employeegroupService.load(id);
		Assert.assertNull(employeegroup);
	}

}
