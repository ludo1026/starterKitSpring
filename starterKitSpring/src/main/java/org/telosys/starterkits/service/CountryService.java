/*
 * Service class 
 * Created on 21 nov. 2013 ( Time 16:18:56 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Country;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.CountryPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


public class CountryService implements IService<Country, String> {

	protected final Logger LOG = LoggerFactory.getLogger(CountryService.class);
	
	private CountryPersistence getCountryPersistence() {
		return PersistenceServiceProvider.getService(CountryPersistence.class);
	}

	public Country load(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Country country;
		try {
			CountryPersistence countryPersistence = getCountryPersistence();
			country = countryPersistence.load(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return country ;
	}

	public Country save(final Country country) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Country countrySaved;
		try {
			CountryPersistence countryPersistence = getCountryPersistence();
			countrySaved = countryPersistence.save(country);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return countrySaved;
	}

	public void delete(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			CountryPersistence countryPersistence = getCountryPersistence();
			countryPersistence.delete(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Country> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Country> countrys;
		try {
			CountryPersistence countryPersistence = getCountryPersistence();
			countrys = countryPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return countrys;
	}

	public List<Country> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Country> countrys;
		try {
			CountryPersistence countryPersistence = getCountryPersistence();
			countrys = countryPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return countrys;
	}

}
