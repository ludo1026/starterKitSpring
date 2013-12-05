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
import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.dao.jpa.CountryDao;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceImplUnitTest {
	
	@InjectMocks
	private CountryServiceImpl countryService;
	@Mock
	private CountryDao countryDao;
	
	@Test
	public void load() {
		// Given
		String code = "test";

		Country country = new Country();
		country.setCode(code);
		
		when(countryDao.load(code)).thenReturn(country);

		// When
		Country countryResult = countryService.load(code);
		
		// Then
		assertEquals(code, countryResult.getCode());
	}
	
	@Test
	public void save() {
		// Given
		Country countryToSave = new Country();
		Country countrySaved = new Country();
		
		when(countryDao.save(countryToSave)).thenReturn(countrySaved);

		// When
		Country countryResult = countryService.save(countryToSave);
		
		// Then
		assertTrue(countryResult != countryToSave);
		assertTrue(countryResult == countrySaved);
	}

	@Test
	public void delete() {
		// Given
		String code = "test";
		
		// When
		countryService.delete(code);
		
		// Then
		verify(countryDao).delete(code);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Country> countrys = new ArrayList<Country>();
		countrys.add(new Country());
		
		when(countryDao.search(criteria)).thenReturn(countrys);
		
		// When
		List<Country> countrysResult = countryService.search(criteria);
		
		// Then
		assertTrue(countrysResult == countrys);
	}

	@Test
	public void loadAll() {
		// Given
		List<Country> countrys = new ArrayList<Country>();
		countrys.add(new Country());
		
		when(countryDao.loadAll()).thenReturn(countrys);
		
		// When
		List<Country> countrysResult = countryService.loadAll();
		
		// Then
		assertTrue(countrysResult == countrys);
	}
	
}
