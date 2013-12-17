package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Synopsis;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class SynopsisServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private SynopsisService synopsisService;

	@Test
	public void cycle_vie_complet() {

		Integer bookId = Integer.valueOf("1");

		Synopsis synopsis = new Synopsis();
		synopsis.setBookId(bookId);
		//synopsis.setName("Test " + bookId);

		// Create
		this.synopsisService.save(synopsis);

		// Search
		synopsis = this.synopsisService.load(bookId);
		Assert.assertNotNull(synopsis);

		// Update
		//synopsis.setName("Test 2 " + bookId);
		synopsis = this.synopsisService.save(synopsis);

		// Search
		synopsis = this.synopsisService.load(bookId);
		//Assert.assertEquals("Test 2 " + bookId, synopsis.getName());

		// Delete
		this.synopsisService.delete(synopsis.getBookId());

		// Search
		synopsis = this.synopsisService.load(bookId);
		Assert.assertNull(synopsis);
	}

}
