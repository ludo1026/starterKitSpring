package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.service.CountryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/applicationContext*.xml")
public class CountryServiceIntegTest {

	@Resource
	private CountryService countryService;

	@Test
	public void createCountry() {
		String code = "7";

		Country country = new Country();
		country.setCode(code);
		country.setName("Test " + code);

		// Test
		this.countryService.save(country);

		Country countryResult = this.countryService.load(code);

		Assert.assertNotNull(countryResult);
	}

}
