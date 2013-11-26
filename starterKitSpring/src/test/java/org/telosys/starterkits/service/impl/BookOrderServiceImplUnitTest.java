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
import org.telosys.starterkits.bean.BookOrder;
import org.telosys.starterkits.dao.jpa.BookOrderDao;

@RunWith(MockitoJUnitRunner.class)
public class BookOrderServiceImplUnitTest {
	
	@InjectMocks
	private BookOrderServiceImpl bookorderService;
	@Mock
	private BookOrderDao bookorderDao;
	
	@Test
	public void load() {
		// Given
		Integer id = Integer.valueOf("1");

		BookOrder bookorder = new BookOrder();
		bookorder.setId(id);
		
		when(bookorderDao.load(id)).thenReturn(bookorder);

		// When
		BookOrder bookorderResult = bookorderService.load(id);
		
		// Then
		assertEquals(id, bookorderResult.getId());
	}
	
	@Test
	public void save() {
		// Given
		BookOrder bookorderToSave = new BookOrder();
		BookOrder bookorderSaved = new BookOrder();
		
		when(bookorderDao.save(bookorderToSave)).thenReturn(bookorderSaved);

		// When
		BookOrder bookorderResult = bookorderService.save(bookorderToSave);
		
		// Then
		assertTrue(bookorderResult != bookorderToSave);
		assertTrue(bookorderResult == bookorderSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer id = Integer.valueOf("1");
		
		// When
		bookorderService.delete(id);
		
		// Then
		verify(bookorderDao).delete(id);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<BookOrder> bookorders = new ArrayList<BookOrder>();
		bookorders.add(new BookOrder());
		
		when(bookorderDao.search(criteria)).thenReturn(bookorders);
		
		// When
		List<BookOrder> bookordersResult = bookorderService.search(criteria);
		
		// Then
		assertTrue(bookordersResult == bookorders);
	}

	@Test
	public void loadAll() {
		// Given
		List<BookOrder> bookorders = new ArrayList<BookOrder>();
		bookorders.add(new BookOrder());
		
		when(bookorderDao.loadAll()).thenReturn(bookorders);
		
		// When
		List<BookOrder> bookordersResult = bookorderService.loadAll();
		
		// Then
		assertTrue(bookordersResult == bookorders);
	}
	
}
