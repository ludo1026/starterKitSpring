package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.dao.jpa.CountryDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Country.
 */
@Component
public class CountryDaoImpl extends DaoImpl<Country, String> implements CountryDao {

	/**
	 * Constructor.
	 */
	public CountryDaoImpl() {
		super(Country.class);
	}

}
