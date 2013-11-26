package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Country;

import org.telosys.starterkits.test.common.AbstractMemoryDBTest;

public class CountryServiceMemoryDBIntegTest extends AbstractMemoryDBTest {

	@Override
	protected String getReferentielDataFilename() {
		return null;
	}

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private CountryService countryService;

	@Test
	public void cycle_vie_complet() {

		String code = "1";

		Country country = new Country();
		country.setCode(code);
		//country.setName("Test " + code);

		// Create
		this.countryService.save(country);

		// Search
		country = this.countryService.load(code);
		Assert.assertNotNull(country);

		// Update
		//country.setName("Test 2 " + code);
		country = this.countryService.save(country);

		// Search
		country = this.countryService.load(code);
		//Assert.assertEquals("Test 2 " + code, country.getName());

		// Delete
		this.countryService.delete(country.getCode());

		// Search
		country = this.countryService.load(code);
		Assert.assertNull(country);
	}

}
