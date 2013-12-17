package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.BookOrder;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class BookOrderServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private BookOrderService bookorderService;

	@Test
	public void cycle_vie_complet() {

		Integer id = Integer.valueOf("1");

		BookOrder bookorder = new BookOrder();
		bookorder.setId(id);
		//bookorder.setName("Test " + id);

		// Create
		this.bookorderService.save(bookorder);

		// Search
		bookorder = this.bookorderService.load(id);
		Assert.assertNotNull(bookorder);

		// Update
		//bookorder.setName("Test 2 " + id);
		bookorder = this.bookorderService.save(bookorder);

		// Search
		bookorder = this.bookorderService.load(id);
		//Assert.assertEquals("Test 2 " + id, bookorder.getName());

		// Delete
		this.bookorderService.delete(bookorder.getId());

		// Search
		bookorder = this.bookorderService.load(id);
		Assert.assertNull(bookorder);
	}

}
