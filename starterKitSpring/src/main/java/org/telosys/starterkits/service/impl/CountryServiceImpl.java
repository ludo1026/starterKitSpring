package org.telosys.starterkits.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.dao.jpa.CountryDao;
import org.telosys.starterkits.service.CountryService;

/**
 * Service : Country.
 */
public class CountryServiceImpl implements CountryService {
		
	@Resource
	private CountryDao countryDao;
	
	public Country load(final String code) {
		return countryDao.load(code);
	}
	
	public Country save(final Country country) {
		return countryDao.save(country);
	}

	public void delete(final String code) {
		countryDao.delete(code);
	}

	public List<Country> search(final Map<String,Object> criteria) {
		return countryDao.search(criteria);
	}

	public List<Country> loadAll() {
		return countryDao.loadAll();
	}
	
}
