package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Author;

import org.telosys.starterkits.test.common.AbstractMemoryDBTest;

public class AuthorServiceMemoryDBIntegTest extends AbstractMemoryDBTest {

	@Override
	protected String getReferentielDataFilename() {
		return null;
	}

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private AuthorService authorService;

	@Test
	public void cycle_vie_complet() {

		Integer id = Integer.valueOf("1");

		Author author = new Author();
		author.setId(id);
		//author.setName("Test " + id);

		// Create
		this.authorService.save(author);

		// Search
		author = this.authorService.load(id);
		Assert.assertNotNull(author);

		// Update
		//author.setName("Test 2 " + id);
		author = this.authorService.save(author);

		// Search
		author = this.authorService.load(id);
		//Assert.assertEquals("Test 2 " + id, author.getName());

		// Delete
		this.authorService.delete(author.getId());

		// Search
		author = this.authorService.load(id);
		Assert.assertNull(author);
	}

}
