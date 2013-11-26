package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Shop;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class ShopServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private ShopService shopService;

	@Test
	public void cycle_vie_complet() {

		String code = "1";

		Shop shop = new Shop();
		shop.setCode(code);
		//shop.setName("Test " + code);

		// Create
		this.shopService.save(shop);

		// Search
		shop = this.shopService.load(code);
		Assert.assertNotNull(shop);

		// Update
		//shop.setName("Test 2 " + code);
		shop = this.shopService.save(shop);

		// Search
		shop = this.shopService.load(code);
		//Assert.assertEquals("Test 2 " + code, shop.getName());

		// Delete
		this.shopService.delete(shop.getCode());

		// Search
		shop = this.shopService.load(code);
		Assert.assertNull(shop);
	}

}
