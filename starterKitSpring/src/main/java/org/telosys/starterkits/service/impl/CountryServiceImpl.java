package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Country load(final String code) {
		return countryRepository.findOne(code);
	}
	
	public Country save(final Country country) {
		return countryRepository.save(country);
	}

	public void delete(final String code) {
		countryRepository.delete(code);
	}

	public List<Country> search(final Map<String,Object> criteria) {
		return countryDao.search(criteria);
	}

	public List<Country> loadAll() {
		List<Country> countrys = new ArrayList<Country>();
		for (Country country : countryRepository.findAll()) {
			countrys.add(country);
		}
		return countrys;
	}
	
	@Transactional(readOnly=true)
	public Page<Country> findAllByPage(Pageable pageable) {
		return countryRepository.findAll(pageable);
	}
	
}
