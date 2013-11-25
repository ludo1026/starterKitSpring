package org.telosys.starterkits.service;

import java.util.Random;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.telosys.starterkits.bean.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/applicationContext*.xml")
public class CountryServiceRealTest {
	
	@Resource
	private CountryService countryService;
	
	@Test
	public void createCountry() {
		String code = "7";
		
		Country country = new Country();
		country.setCode(code);
		country.setName("Test "+code);
		
		// Test
		countryService.save(country);
		
		Country countryResult = countryService.load(code);
		
		Assert.assertNotNull(countryResult);
	}
	
}
