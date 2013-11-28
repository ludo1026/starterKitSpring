package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.dao.jpa.CountryDao;
import org.telosys.starterkits.dao.repository.CountryRepository;
import org.telosys.starterkits.service.CountryService;

/**
 * Service : Country.
 */
@Component
@Transactional
public class CountryServiceImpl implements CountryService {

	@Resource
	private CountryDao countryDao;
	@Resource
	private CountryRepository countryRepository;

	@Override
	public Country load(final String code) {
		return this.countryRepository.findOne(code);
	}

	@Override
	public Country save(final Country country) {
		return this.countryRepository.save(country);
	}

	@Override
	public void delete(final String code) {
		this.countryRepository.delete(code);
	}

	@Override
	public List<Country> search(final Map<String, Object> criteria) {
		return this.countryDao.search(criteria);
	}

	@Override
	public List<Country> loadAll() {
		List<Country> countrys = new ArrayList<Country>();
		for (Country country : this.countryRepository.findAll()) {
			countrys.add(country);
		}
		return countrys;
	}
}
