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
import org.telosys.starterkits.bean.Publisher;

@RunWith(MockitoJUnitRunner.class)
public class PublisherDaoUnitTest {

	@InjectMocks
	private PublisherDaoImpl publisherDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		Integer code = Integer.valueOf("1");

		Publisher publisher = new Publisher();
		publisher.setCode(code);

		when(entityManager.find(Publisher.class, code)).thenReturn(publisher);

		// When
		Publisher publisherResult = publisherDao.load(code);

		// Then
		assertEquals(code, publisherResult.getCode());
	}

	@Test
	public void save() {
		// Given
		Publisher publisherToSave = new Publisher();
		Publisher publisherSaved = new Publisher();

		when(entityManager.merge(publisherToSave)).thenReturn(publisherSaved);

		// When
		Publisher publisherResult = publisherDao.save(publisherToSave);

		// Then
		assertTrue(publisherResult != publisherToSave);
		assertTrue(publisherResult == publisherSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer code = Integer.valueOf("1");

		Publisher publisher = new Publisher();
		when(entityManager.find(Publisher.class, code)).thenReturn(publisher);

		// When
		publisherDao.delete(code);

		// Then
		verify(entityManager).remove(publisher);
	}

	@Test
	public void loadAll() {
		// Given
		List<Publisher> publishers = new ArrayList<Publisher>();
		publishers.add(new Publisher());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Publisher.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(publishers);

		// When
		List<Publisher> publishersResult = publisherDao.loadAll();

		// Then
		assertTrue(publishersResult == publishers);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Publisher> publishers = new ArrayList<Publisher>();
		publishers.add(new Publisher());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Publisher.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(publishers);

		// When
		List<Publisher> publishersResult = publisherDao.search(null);

		// Then
		assertTrue(publishersResult == publishers);
	}

}
