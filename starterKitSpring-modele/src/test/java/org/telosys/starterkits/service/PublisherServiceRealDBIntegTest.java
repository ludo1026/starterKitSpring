package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Publisher;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class PublisherServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private PublisherService publisherService;

	@Test
	public void cycle_vie_complet() {

		Integer code = Integer.valueOf("1");

		Publisher publisher = new Publisher();
		publisher.setCode(code);
		//publisher.setName("Test " + code);

		// Create
		this.publisherService.save(publisher);

		// Search
		publisher = this.publisherService.load(code);
		Assert.assertNotNull(publisher);

		// Update
		//publisher.setName("Test 2 " + code);
		publisher = this.publisherService.save(publisher);

		// Search
		publisher = this.publisherService.load(code);
		//Assert.assertEquals("Test 2 " + code, publisher.getName());

		// Delete
		this.publisherService.delete(publisher.getCode());

		// Search
		publisher = this.publisherService.load(code);
		Assert.assertNull(publisher);
	}

}
