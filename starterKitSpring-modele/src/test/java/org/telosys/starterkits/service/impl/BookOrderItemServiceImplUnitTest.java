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
import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;
import org.telosys.starterkits.dao.jpa.BookOrderItemDao;

@RunWith(MockitoJUnitRunner.class)
public class BookOrderItemServiceImplUnitTest {
	
	@InjectMocks
	private BookOrderItemServiceImpl bookorderitemService;
	@Mock
	private BookOrderItemDao bookorderitemDao;
	
	@Test
	public void load() {
		// Given
		BookOrderItemId id = new BookOrderItemId();

		BookOrderItem bookorderitem = new BookOrderItem();
		bookorderitem.setId(id);
		
		when(bookorderitemDao.load(id)).thenReturn(bookorderitem);

		// When
		BookOrderItem bookorderitemResult = bookorderitemService.load(id);
		
		// Then
		assertEquals(id, bookorderitemResult.getId());
	}
	
	@Test
	public void save() {
		// Given
		BookOrderItem bookorderitemToSave = new BookOrderItem();
		BookOrderItem bookorderitemSaved = new BookOrderItem();
		
		when(bookorderitemDao.save(bookorderitemToSave)).thenReturn(bookorderitemSaved);

		// When
		BookOrderItem bookorderitemResult = bookorderitemService.save(bookorderitemToSave);
		
		// Then
		assertTrue(bookorderitemResult != bookorderitemToSave);
		assertTrue(bookorderitemResult == bookorderitemSaved);
	}

	@Test
	public void delete() {
		// Given
		BookOrderItemId id = new BookOrderItemId();
		
		// When
		bookorderitemService.delete(id);
		
		// Then
		verify(bookorderitemDao).delete(id);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<BookOrderItem> bookorderitems = new ArrayList<BookOrderItem>();
		bookorderitems.add(new BookOrderItem());
		
		when(bookorderitemDao.search(criteria)).thenReturn(bookorderitems);
		
		// When
		List<BookOrderItem> bookorderitemsResult = bookorderitemService.search(criteria);
		
		// Then
		assertTrue(bookorderitemsResult == bookorderitems);
	}

	@Test
	public void loadAll() {
		// Given
		List<BookOrderItem> bookorderitems = new ArrayList<BookOrderItem>();
		bookorderitems.add(new BookOrderItem());
		
		when(bookorderitemDao.loadAll()).thenReturn(bookorderitems);
		
		// When
		List<BookOrderItem> bookorderitemsResult = bookorderitemService.loadAll();
		
		// Then
		assertTrue(bookorderitemsResult == bookorderitems);
	}
	
}
