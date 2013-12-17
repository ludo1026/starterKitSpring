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
import org.telosys.starterkits.bean.BookOrder;

@RunWith(MockitoJUnitRunner.class)
public class BookOrderDaoUnitTest {

	@InjectMocks
	private BookOrderDaoImpl bookorderDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		Integer id = Integer.valueOf("1");

		BookOrder bookorder = new BookOrder();
		bookorder.setId(id);

		when(entityManager.find(BookOrder.class, id)).thenReturn(bookorder);

		// When
		BookOrder bookorderResult = bookorderDao.load(id);

		// Then
		assertEquals(id, bookorderResult.getId());
	}

	@Test
	public void save() {
		// Given
		BookOrder bookorderToSave = new BookOrder();
		BookOrder bookorderSaved = new BookOrder();

		when(entityManager.merge(bookorderToSave)).thenReturn(bookorderSaved);

		// When
		BookOrder bookorderResult = bookorderDao.save(bookorderToSave);

		// Then
		assertTrue(bookorderResult != bookorderToSave);
		assertTrue(bookorderResult == bookorderSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer id = Integer.valueOf("1");

		BookOrder bookorder = new BookOrder();
		when(entityManager.find(BookOrder.class, id)).thenReturn(bookorder);

		// When
		bookorderDao.delete(id);

		// Then
		verify(entityManager).remove(bookorder);
	}

	@Test
	public void loadAll() {
		// Given
		List<BookOrder> bookorders = new ArrayList<BookOrder>();
		bookorders.add(new BookOrder());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + BookOrder.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(bookorders);

		// When
		List<BookOrder> bookordersResult = bookorderDao.loadAll();

		// Then
		assertTrue(bookordersResult == bookorders);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<BookOrder> bookorders = new ArrayList<BookOrder>();
		bookorders.add(new BookOrder());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + BookOrder.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(bookorders);

		// When
		List<BookOrder> bookordersResult = bookorderDao.search(null);

		// Then
		assertTrue(bookordersResult == bookorders);
	}

}
