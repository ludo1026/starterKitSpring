/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 17:59:33 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.service.base.Service;


/**
 * Service : Country.
 */
public interface CountryService extends Service<Country, String> {
	
	Country load(final String code);

	Country save(final Country country);

	void delete(final String code);

	List<Country> search(final Map<String,Object> criteria);

	List<Country> loadAll();

}
