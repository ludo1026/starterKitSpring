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
import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.dao.jpa.PublisherDao;

@RunWith(MockitoJUnitRunner.class)
public class PublisherServiceImplUnitTest {
	
	@InjectMocks
	private PublisherServiceImpl publisherService;
	@Mock
	private PublisherDao publisherDao;
	
	@Test
	public void load() {
		// Given
		Integer code = Integer.valueOf("1");

		Publisher publisher = new Publisher();
		publisher.setCode(code);
		
		when(publisherDao.load(code)).thenReturn(publisher);

		// When
		Publisher publisherResult = publisherService.load(code);
		
		// Then
		assertEquals(code, publisherResult.getCode());
	}
	
	@Test
	public void save() {
		// Given
		Publisher publisherToSave = new Publisher();
		Publisher publisherSaved = new Publisher();
		
		when(publisherDao.save(publisherToSave)).thenReturn(publisherSaved);

		// When
		Publisher publisherResult = publisherService.save(publisherToSave);
		
		// Then
		assertTrue(publisherResult != publisherToSave);
		assertTrue(publisherResult == publisherSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer code = Integer.valueOf("1");
		
		// When
		publisherService.delete(code);
		
		// Then
		verify(publisherDao).delete(code);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Publisher> publishers = new ArrayList<Publisher>();
		publishers.add(new Publisher());
		
		when(publisherDao.search(criteria)).thenReturn(publishers);
		
		// When
		List<Publisher> publishersResult = publisherService.search(criteria);
		
		// Then
		assertTrue(publishersResult == publishers);
	}

	@Test
	public void loadAll() {
		// Given
		List<Publisher> publishers = new ArrayList<Publisher>();
		publishers.add(new Publisher());
		
		when(publisherDao.loadAll()).thenReturn(publishers);
		
		// When
		List<Publisher> publishersResult = publisherService.loadAll();
		
		// Then
		assertTrue(publishersResult == publishers);
	}
	
}
