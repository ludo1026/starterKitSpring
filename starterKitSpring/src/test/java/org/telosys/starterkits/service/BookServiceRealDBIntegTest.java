package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Book;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class BookServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private BookService bookService;

	@Test
	public void cycle_vie_complet() {

		Integer id = Integer.valueOf("1");

		Book book = new Book();
		book.setId(id);
		//book.setName("Test " + id);

		// Create
		this.bookService.save(book);

		// Search
		book = this.bookService.load(id);
		Assert.assertNotNull(book);

		// Update
		//book.setName("Test 2 " + id);
		book = this.bookService.save(book);

		// Search
		book = this.bookService.load(id);
		//Assert.assertEquals("Test 2 " + id, book.getName());

		// Delete
		this.bookService.delete(book.getId());

		// Search
		book = this.bookService.load(id);
		Assert.assertNull(book);
	}

}
