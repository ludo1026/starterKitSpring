package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class BookOrderItemServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private BookOrderItemService bookorderitemService;

	@Test
	public void cycle_vie_complet() {

		BookOrderItemId id = new BookOrderItemId();

		BookOrderItem bookorderitem = new BookOrderItem();
		bookorderitem.setId(id);
		//bookorderitem.setName("Test " + id);

		// Create
		this.bookorderitemService.save(bookorderitem);

		// Search
		bookorderitem = this.bookorderitemService.load(id);
		Assert.assertNotNull(bookorderitem);

		// Update
		//bookorderitem.setName("Test 2 " + id);
		bookorderitem = this.bookorderitemService.save(bookorderitem);

		// Search
		bookorderitem = this.bookorderitemService.load(id);
		//Assert.assertEquals("Test 2 " + id, bookorderitem.getName());

		// Delete
		this.bookorderitemService.delete(bookorderitem.getId());

		// Search
		bookorderitem = this.bookorderitemService.load(id);
		Assert.assertNull(bookorderitem);
	}

}
