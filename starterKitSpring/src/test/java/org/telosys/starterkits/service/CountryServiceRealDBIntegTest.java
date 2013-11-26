package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class CountryServiceRealDBIntegTest extends AbstractRealDBTest {

	@Resource
	private CountryService countryService;

	@Test
	public void createCountry() {
		String code = "8";

		Country country = new Country();
		country.setCode(code);
		country.setName("Test " + code);

		// Test
		this.countryService.save(country);

		Country countryResult = this.countryService.load(code);

		Assert.assertNotNull(countryResult);
	}

	@Override
	protected String getDataSetFile() {
		// TODO Auto-generated method stub
		return null;
	}

}
