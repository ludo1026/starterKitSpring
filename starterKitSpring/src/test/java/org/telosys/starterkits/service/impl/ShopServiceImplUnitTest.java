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
import org.telosys.starterkits.bean.Shop;
import org.telosys.starterkits.dao.jpa.ShopDao;

@RunWith(MockitoJUnitRunner.class)
public class ShopServiceImplUnitTest {
	
	@InjectMocks
	private ShopServiceImpl shopService;
	@Mock
	private ShopDao shopDao;
	
	@Test
	public void load() {
		// Given
		String code = "test";

		Shop shop = new Shop();
		shop.setCode(code);
		
		when(shopDao.load(code)).thenReturn(shop);

		// When
		Shop shopResult = shopService.load(code);
		
		// Then
		assertEquals(code, shopResult.getCode());
	}
	
	@Test
	public void save() {
		// Given
		Shop shopToSave = new Shop();
		Shop shopSaved = new Shop();
		
		when(shopDao.save(shopToSave)).thenReturn(shopSaved);

		// When
		Shop shopResult = shopService.save(shopToSave);
		
		// Then
		assertTrue(shopResult != shopToSave);
		assertTrue(shopResult == shopSaved);
	}

	@Test
	public void delete() {
		// Given
		String code = "test";
		
		// When
		shopService.delete(code);
		
		// Then
		verify(shopDao).delete(code);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Shop> shops = new ArrayList<Shop>();
		shops.add(new Shop());
		
		when(shopDao.search(criteria)).thenReturn(shops);
		
		// When
		List<Shop> shopsResult = shopService.search(criteria);
		
		// Then
		assertTrue(shopsResult == shops);
	}

	@Test
	public void loadAll() {
		// Given
		List<Shop> shops = new ArrayList<Shop>();
		shops.add(new Shop());
		
		when(shopDao.loadAll()).thenReturn(shops);
		
		// When
		List<Shop> shopsResult = shopService.loadAll();
		
		// Then
		assertTrue(shopsResult == shops);
	}
	
}
