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
import org.telosys.starterkits.bean.Country;

@RunWith(MockitoJUnitRunner.class)
public class CountryDaoUnitTest {

	@InjectMocks
	private CountryDaoImpl countryDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		String code = "test";

		Country country = new Country();
		country.setCode(code);

		when(entityManager.find(Country.class, code)).thenReturn(country);

		// When
		Country countryResult = countryDao.load(code);

		// Then
		assertEquals(code, countryResult.getCode());
	}

	@Test
	public void save() {
		// Given
		Country countryToSave = new Country();
		Country countrySaved = new Country();

		when(entityManager.merge(countryToSave)).thenReturn(countrySaved);

		// When
		Country countryResult = countryDao.save(countryToSave);

		// Then
		assertTrue(countryResult != countryToSave);
		assertTrue(countryResult == countrySaved);
	}

	@Test
	public void delete() {
		// Given
		String code = "test";

		Country country = new Country();
		when(entityManager.find(Country.class, code)).thenReturn(country);

		// When
		countryDao.delete(code);

		// Then
		verify(entityManager).remove(country);
	}

	@Test
	public void loadAll() {
		// Given
		List<Country> countrys = new ArrayList<Country>();
		countrys.add(new Country());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Country.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(countrys);

		// When
		List<Country> countrysResult = countryDao.loadAll();

		// Then
		assertTrue(countrysResult == countrys);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Country> countrys = new ArrayList<Country>();
		countrys.add(new Country());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Country.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(countrys);

		// When
		List<Country> countrysResult = countryDao.search(null);

		// Then
		assertTrue(countrysResult == countrys);
	}

}
