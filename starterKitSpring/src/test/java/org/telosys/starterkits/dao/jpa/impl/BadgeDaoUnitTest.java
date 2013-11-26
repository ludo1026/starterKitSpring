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
import org.telosys.starterkits.bean.Badge;

@RunWith(MockitoJUnitRunner.class)
public class BadgeDaoUnitTest {

	@InjectMocks
	private BadgeDaoImpl badgeDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		Integer badgeNumber = Integer.valueOf("1");

		Badge badge = new Badge();
		badge.setBadgeNumber(badgeNumber);

		when(entityManager.find(Badge.class, badgeNumber)).thenReturn(badge);

		// When
		Badge badgeResult = badgeDao.load(badgeNumber);

		// Then
		assertEquals(badgeNumber, badgeResult.getBadgeNumber());
	}

	@Test
	public void save() {
		// Given
		Badge badgeToSave = new Badge();
		Badge badgeSaved = new Badge();

		when(entityManager.merge(badgeToSave)).thenReturn(badgeSaved);

		// When
		Badge badgeResult = badgeDao.save(badgeToSave);

		// Then
		assertTrue(badgeResult != badgeToSave);
		assertTrue(badgeResult == badgeSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer badgeNumber = Integer.valueOf("1");

		Badge badge = new Badge();
		when(entityManager.find(Badge.class, badgeNumber)).thenReturn(badge);

		// When
		badgeDao.delete(badgeNumber);

		// Then
		verify(entityManager).remove(badge);
	}

	@Test
	public void loadAll() {
		// Given
		List<Badge> badges = new ArrayList<Badge>();
		badges.add(new Badge());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Badge.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(badges);

		// When
		List<Badge> badgesResult = badgeDao.loadAll();

		// Then
		assertTrue(badgesResult == badges);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Badge> badges = new ArrayList<Badge>();
		badges.add(new Badge());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Badge.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(badges);

		// When
		List<Badge> badgesResult = badgeDao.search(null);

		// Then
		assertTrue(badgesResult == badges);
	}

}
