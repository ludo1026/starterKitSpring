package org.telosys.starterkits.dao.jpa.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telosys.starterkits.bean.Book;

@RunWith(MockitoJUnitRunner.class)
public class BookDaoUnitTest {

	@InjectMocks
	private BookDaoImpl bookDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		Integer id = Integer.valueOf("1");

		Book book = new Book();
		book.setId(id);

		when(entityManager.find(Book.class, id)).thenReturn(book);

		// When
		Book bookResult = bookDao.load(id);

		// Then
		assertEquals(id, bookResult.getId());
	}

	@Test
	public void save() {
		// Given
		Book bookToSave = new Book();
		Book bookSaved = new Book();

		when(entityManager.merge(bookToSave)).thenReturn(bookSaved);

		// When
		Book bookResult = bookDao.save(bookToSave);

		// Then
		assertTrue(bookResult != bookToSave);
		assertTrue(bookResult == bookSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer id = Integer.valueOf("1");

		Book book = new Book();
		when(entityManager.find(Book.class, id)).thenReturn(book);

		// When
		bookDao.delete(id);

		// Then
		verify(entityManager).remove(book);
	}

	@Test
	public void loadAll() {
		// Given
		List<Book> books = new ArrayList<Book>();
		books.add(new Book());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Book.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(books);

		// When
		List<Book> booksResult = bookDao.loadAll();

		// Then
		assertTrue(booksResult == books);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Book> books = new ArrayList<Book>();
		books.add(new Book());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Book.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(books);

		// When
		List<Book> booksResult = bookDao.search(null);

		// Then
		assertTrue(booksResult == books);
	}

}
