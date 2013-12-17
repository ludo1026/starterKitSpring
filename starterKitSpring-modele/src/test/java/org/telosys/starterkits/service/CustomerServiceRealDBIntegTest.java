package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Customer;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class CustomerServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private CustomerService customerService;

	@Test
	public void cycle_vie_complet() {

		String code = "1";

		Customer customer = new Customer();
		customer.setCode(code);
		//customer.setName("Test " + code);

		// Create
		this.customerService.save(customer);

		// Search
		customer = this.customerService.load(code);
		Assert.assertNotNull(customer);

		// Update
		//customer.setName("Test 2 " + code);
		customer = this.customerService.save(customer);

		// Search
		customer = this.customerService.load(code);
		//Assert.assertEquals("Test 2 " + code, customer.getName());

		// Delete
		this.customerService.delete(customer.getCode());

		// Search
		customer = this.customerService.load(code);
		Assert.assertNull(customer);
	}

}
