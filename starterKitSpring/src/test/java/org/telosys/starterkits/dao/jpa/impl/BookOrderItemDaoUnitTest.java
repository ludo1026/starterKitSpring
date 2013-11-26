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
import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;

@RunWith(MockitoJUnitRunner.class)
public class BookOrderItemDaoUnitTest {

	@InjectMocks
	private BookOrderItemDaoImpl bookorderitemDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		BookOrderItemId id = new BookOrderItemId();

		BookOrderItem bookorderitem = new BookOrderItem();
		bookorderitem.setId(id);

		when(entityManager.find(BookOrderItem.class, id)).thenReturn(bookorderitem);

		// When
		BookOrderItem bookorderitemResult = bookorderitemDao.load(id);

		// Then
		assertEquals(id, bookorderitemResult.getId());
	}

	@Test
	public void save() {
		// Given
		BookOrderItem bookorderitemToSave = new BookOrderItem();
		BookOrderItem bookorderitemSaved = new BookOrderItem();

		when(entityManager.merge(bookorderitemToSave)).thenReturn(bookorderitemSaved);

		// When
		BookOrderItem bookorderitemResult = bookorderitemDao.save(bookorderitemToSave);

		// Then
		assertTrue(bookorderitemResult != bookorderitemToSave);
		assertTrue(bookorderitemResult == bookorderitemSaved);
	}

	@Test
	public void delete() {
		// Given
		BookOrderItemId id = new BookOrderItemId();

		BookOrderItem bookorderitem = new BookOrderItem();
		when(entityManager.find(BookOrderItem.class, id)).thenReturn(bookorderitem);

		// When
		bookorderitemDao.delete(id);

		// Then
		verify(entityManager).remove(bookorderitem);
	}

	@Test
	public void loadAll() {
		// Given
		List<BookOrderItem> bookorderitems = new ArrayList<BookOrderItem>();
		bookorderitems.add(new BookOrderItem());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + BookOrderItem.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(bookorderitems);

		// When
		List<BookOrderItem> bookorderitemsResult = bookorderitemDao.loadAll();

		// Then
		assertTrue(bookorderitemsResult == bookorderitems);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<BookOrderItem> bookorderitems = new ArrayList<BookOrderItem>();
		bookorderitems.add(new BookOrderItem());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + BookOrderItem.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(bookorderitems);

		// When
		List<BookOrderItem> bookorderitemsResult = bookorderitemDao.search(null);

		// Then
		assertTrue(bookorderitemsResult == bookorderitems);
	}

}
