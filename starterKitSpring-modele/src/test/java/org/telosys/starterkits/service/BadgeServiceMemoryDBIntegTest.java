package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Badge;

import org.telosys.starterkits.test.common.AbstractMemoryDBTest;

public class BadgeServiceMemoryDBIntegTest extends AbstractMemoryDBTest {

	@Override
	protected String getReferentielDataFilename() {
		return null;
	}

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private BadgeService badgeService;

	@Test
	public void cycle_vie_complet() {

		Integer badgeNumber = Integer.valueOf("1");

		Badge badge = new Badge();
		badge.setBadgeNumber(badgeNumber);
		//badge.setName("Test " + badgeNumber);

		// Create
		this.badgeService.save(badge);

		// Search
		badge = this.badgeService.load(badgeNumber);
		Assert.assertNotNull(badge);

		// Update
		//badge.setName("Test 2 " + badgeNumber);
		badge = this.badgeService.save(badge);

		// Search
		badge = this.badgeService.load(badgeNumber);
		//Assert.assertEquals("Test 2 " + badgeNumber, badge.getName());

		// Delete
		this.badgeService.delete(badge.getBadgeNumber());

		// Search
		badge = this.badgeService.load(badgeNumber);
		Assert.assertNull(badge);
	}

}
