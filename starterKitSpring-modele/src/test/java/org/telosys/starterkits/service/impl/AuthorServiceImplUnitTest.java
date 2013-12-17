package org.telosys.starterkits.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.dao.jpa.AuthorDao;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplUnitTest {
	
	@InjectMocks
	private AuthorServiceImpl authorService;
	@Mock
	private AuthorDao authorDao;
	
	@Test
	public void load() {
		// Given
		Integer id = Integer.valueOf("1");

		Author author = new Author();
		author.setId(id);
		
		when(authorDao.load(id)).thenReturn(author);

		// When
		Author authorResult = authorService.load(id);
		
		// Then
		assertEquals(id, authorResult.getId());
	}
	
	@Test
	public void save() {
		// Given
		Author authorToSave = new Author();
		Author authorSaved = new Author();
		
		when(authorDao.save(authorToSave)).thenReturn(authorSaved);

		// When
		Author authorResult = authorService.save(authorToSave);
		
		// Then
		assertTrue(authorResult != authorToSave);
		assertTrue(authorResult == authorSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer id = Integer.valueOf("1");
		
		// When
		authorService.delete(id);
		
		// Then
		verify(authorDao).delete(id);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Author> authors = new ArrayList<Author>();
		authors.add(new Author());
		
		when(authorDao.search(criteria)).thenReturn(authors);
		
		// When
		List<Author> authorsResult = authorService.search(criteria);
		
		// Then
		assertTrue(authorsResult == authors);
	}

	@Test
	public void loadAll() {
		// Given
		List<Author> authors = new ArrayList<Author>();
		authors.add(new Author());
		
		when(authorDao.loadAll()).thenReturn(authors);
		
		// When
		List<Author> authorsResult = authorService.loadAll();
		
		// Then
		assertTrue(authorsResult == authors);
	}
	
}
