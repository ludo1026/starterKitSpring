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
import org.telosys.starterkits.bean.Book;
import org.telosys.starterkits.dao.jpa.BookDao;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplUnitTest {
	
	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;
	
	@Test
	public void load() {
		// Given
		Integer id = Integer.valueOf("1");

		Book book = new Book();
		book.setId(id);
		
		when(bookDao.load(id)).thenReturn(book);

		// When
		Book bookResult = bookService.load(id);
		
		// Then
		assertEquals(id, bookResult.getId());
	}
	
	@Test
	public void save() {
		// Given
		Book bookToSave = new Book();
		Book bookSaved = new Book();
		
		when(bookDao.save(bookToSave)).thenReturn(bookSaved);

		// When
		Book bookResult = bookService.save(bookToSave);
		
		// Then
		assertTrue(bookResult != bookToSave);
		assertTrue(bookResult == bookSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer id = Integer.valueOf("1");
		
		// When
		bookService.delete(id);
		
		// Then
		verify(bookDao).delete(id);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Book> books = new ArrayList<Book>();
		books.add(new Book());
		
		when(bookDao.search(criteria)).thenReturn(books);
		
		// When
		List<Book> booksResult = bookService.search(criteria);
		
		// Then
		assertTrue(booksResult == books);
	}

	@Test
	public void loadAll() {
		// Given
		List<Book> books = new ArrayList<Book>();
		books.add(new Book());
		
		when(bookDao.loadAll()).thenReturn(books);
		
		// When
		List<Book> booksResult = bookService.loadAll();
		
		// Then
		assertTrue(booksResult == books);
	}
	
}
