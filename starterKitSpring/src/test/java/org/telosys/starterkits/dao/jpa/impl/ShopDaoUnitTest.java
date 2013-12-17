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
import org.telosys.starterkits.bean.Shop;

@RunWith(MockitoJUnitRunner.class)
public class ShopDaoUnitTest {

	@InjectMocks
	private ShopDaoImpl shopDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		String code = "test";

		Shop shop = new Shop();
		shop.setCode(code);

		when(entityManager.find(Shop.class, code)).thenReturn(shop);

		// When
		Shop shopResult = shopDao.load(code);

		// Then
		assertEquals(code, shopResult.getCode());
	}

	@Test
	public void save() {
		// Given
		Shop shopToSave = new Shop();
		Shop shopSaved = new Shop();

		when(entityManager.merge(shopToSave)).thenReturn(shopSaved);

		// When
		Shop shopResult = shopDao.save(shopToSave);

		// Then
		assertTrue(shopResult != shopToSave);
		assertTrue(shopResult == shopSaved);
	}

	@Test
	public void delete() {
		// Given
		String code = "test";

		Shop shop = new Shop();
		when(entityManager.find(Shop.class, code)).thenReturn(shop);

		// When
		shopDao.delete(code);

		// Then
		verify(entityManager).remove(shop);
	}

	@Test
	public void loadAll() {
		// Given
		List<Shop> shops = new ArrayList<Shop>();
		shops.add(new Shop());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Shop.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(shops);

		// When
		List<Shop> shopsResult = shopDao.loadAll();

		// Then
		assertTrue(shopsResult == shops);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Shop> shops = new ArrayList<Shop>();
		shops.add(new Shop());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Shop.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(shops);

		// When
		List<Shop> shopsResult = shopDao.search(null);

		// Then
		assertTrue(shopsResult == shops);
	}

}
