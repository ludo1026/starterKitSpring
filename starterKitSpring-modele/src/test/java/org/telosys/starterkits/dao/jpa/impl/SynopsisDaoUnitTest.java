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
import org.telosys.starterkits.bean.Synopsis;

@RunWith(MockitoJUnitRunner.class)
public class SynopsisDaoUnitTest {

	@InjectMocks
	private SynopsisDaoImpl synopsisDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		Integer bookId = Integer.valueOf("1");

		Synopsis synopsis = new Synopsis();
		synopsis.setBookId(bookId);

		when(entityManager.find(Synopsis.class, bookId)).thenReturn(synopsis);

		// When
		Synopsis synopsisResult = synopsisDao.load(bookId);

		// Then
		assertEquals(bookId, synopsisResult.getBookId());
	}

	@Test
	public void save() {
		// Given
		Synopsis synopsisToSave = new Synopsis();
		Synopsis synopsisSaved = new Synopsis();

		when(entityManager.merge(synopsisToSave)).thenReturn(synopsisSaved);

		// When
		Synopsis synopsisResult = synopsisDao.save(synopsisToSave);

		// Then
		assertTrue(synopsisResult != synopsisToSave);
		assertTrue(synopsisResult == synopsisSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer bookId = Integer.valueOf("1");

		Synopsis synopsis = new Synopsis();
		when(entityManager.find(Synopsis.class, bookId)).thenReturn(synopsis);

		// When
		synopsisDao.delete(bookId);

		// Then
		verify(entityManager).remove(synopsis);
	}

	@Test
	public void loadAll() {
		// Given
		List<Synopsis> synopsiss = new ArrayList<Synopsis>();
		synopsiss.add(new Synopsis());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Synopsis.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(synopsiss);

		// When
		List<Synopsis> synopsissResult = synopsisDao.loadAll();

		// Then
		assertTrue(synopsissResult == synopsiss);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Synopsis> synopsiss = new ArrayList<Synopsis>();
		synopsiss.add(new Synopsis());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Synopsis.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(synopsiss);

		// When
		List<Synopsis> synopsissResult = synopsisDao.search(null);

		// Then
		assertTrue(synopsissResult == synopsiss);
	}

}
