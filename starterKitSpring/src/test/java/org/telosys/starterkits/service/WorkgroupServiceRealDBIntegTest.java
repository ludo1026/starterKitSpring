package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Workgroup;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class WorkgroupServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private WorkgroupService workgroupService;

	@Test
	public void cycle_vie_complet() {

		Short id = Short.valueOf("1");

		Workgroup workgroup = new Workgroup();
		workgroup.setId(id);
		//workgroup.setName("Test " + id);

		// Create
		this.workgroupService.save(workgroup);

		// Search
		workgroup = this.workgroupService.load(id);
		Assert.assertNotNull(workgroup);

		// Update
		//workgroup.setName("Test 2 " + id);
		workgroup = this.workgroupService.save(workgroup);

		// Search
		workgroup = this.workgroupService.load(id);
		//Assert.assertEquals("Test 2 " + id, workgroup.getName());

		// Delete
		this.workgroupService.delete(workgroup.getId());

		// Search
		workgroup = this.workgroupService.load(id);
		Assert.assertNull(workgroup);
	}

}
