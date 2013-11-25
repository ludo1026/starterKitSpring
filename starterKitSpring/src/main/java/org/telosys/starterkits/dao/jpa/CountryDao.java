package org.telosys.starterkits.dao.jpa;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.dao.jpa.base.Dao;

/**
 * DAO : Country.
 */
public interface CountryDao extends Dao<Country, String> {

}
