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
import org.telosys.starterkits.bean.Badge;
import org.telosys.starterkits.dao.jpa.BadgeDao;

@RunWith(MockitoJUnitRunner.class)
public class BadgeServiceImplUnitTest {
	
	@InjectMocks
	private BadgeServiceImpl badgeService;
	@Mock
	private BadgeDao badgeDao;
	
	@Test
	public void load() {
		// Given
		Integer badgeNumber = Integer.valueOf("1");

		Badge badge = new Badge();
		badge.setBadgeNumber(badgeNumber);
		
		when(badgeDao.load(badgeNumber)).thenReturn(badge);

		// When
		Badge badgeResult = badgeService.load(badgeNumber);
		
		// Then
		assertEquals(badgeNumber, badgeResult.getBadgeNumber());
	}
	
	@Test
	public void save() {
		// Given
		Badge badgeToSave = new Badge();
		Badge badgeSaved = new Badge();
		
		when(badgeDao.save(badgeToSave)).thenReturn(badgeSaved);

		// When
		Badge badgeResult = badgeService.save(badgeToSave);
		
		// Then
		assertTrue(badgeResult != badgeToSave);
		assertTrue(badgeResult == badgeSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer badgeNumber = Integer.valueOf("1");
		
		// When
		badgeService.delete(badgeNumber);
		
		// Then
		verify(badgeDao).delete(badgeNumber);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Badge> badges = new ArrayList<Badge>();
		badges.add(new Badge());
		
		when(badgeDao.search(criteria)).thenReturn(badges);
		
		// When
		List<Badge> badgesResult = badgeService.search(criteria);
		
		// Then
		assertTrue(badgesResult == badges);
	}

	@Test
	public void loadAll() {
		// Given
		List<Badge> badges = new ArrayList<Badge>();
		badges.add(new Badge());
		
		when(badgeDao.loadAll()).thenReturn(badges);
		
		// When
		List<Badge> badgesResult = badgeService.loadAll();
		
		// Then
		assertTrue(badgesResult == badges);
	}
	
}
