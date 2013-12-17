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
import org.telosys.starterkits.bean.Synopsis;
import org.telosys.starterkits.dao.jpa.SynopsisDao;

@RunWith(MockitoJUnitRunner.class)
public class SynopsisServiceImplUnitTest {
	
	@InjectMocks
	private SynopsisServiceImpl synopsisService;
	@Mock
	private SynopsisDao synopsisDao;
	
	@Test
	public void load() {
		// Given
		Integer bookId = Integer.valueOf("1");

		Synopsis synopsis = new Synopsis();
		synopsis.setBookId(bookId);
		
		when(synopsisDao.load(bookId)).thenReturn(synopsis);

		// When
		Synopsis synopsisResult = synopsisService.load(bookId);
		
		// Then
		assertEquals(bookId, synopsisResult.getBookId());
	}
	
	@Test
	public void save() {
		// Given
		Synopsis synopsisToSave = new Synopsis();
		Synopsis synopsisSaved = new Synopsis();
		
		when(synopsisDao.save(synopsisToSave)).thenReturn(synopsisSaved);

		// When
		Synopsis synopsisResult = synopsisService.save(synopsisToSave);
		
		// Then
		assertTrue(synopsisResult != synopsisToSave);
		assertTrue(synopsisResult == synopsisSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer bookId = Integer.valueOf("1");
		
		// When
		synopsisService.delete(bookId);
		
		// Then
		verify(synopsisDao).delete(bookId);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Synopsis> synopsiss = new ArrayList<Synopsis>();
		synopsiss.add(new Synopsis());
		
		when(synopsisDao.search(criteria)).thenReturn(synopsiss);
		
		// When
		List<Synopsis> synopsissResult = synopsisService.search(criteria);
		
		// Then
		assertTrue(synopsissResult == synopsiss);
	}

	@Test
	public void loadAll() {
		// Given
		List<Synopsis> synopsiss = new ArrayList<Synopsis>();
		synopsiss.add(new Synopsis());
		
		when(synopsisDao.loadAll()).thenReturn(synopsiss);
		
		// When
		List<Synopsis> synopsissResult = synopsisService.loadAll();
		
		// Then
		assertTrue(synopsissResult == synopsiss);
	}
	
}
