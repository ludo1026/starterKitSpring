package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Synopsis;
import org.telosys.starterkits.dao.jpa.SynopsisDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Synopsis.
 */
@Component
public class SynopsisDaoImpl extends DaoImpl<Synopsis, Integer> implements SynopsisDao {

	/**
	 * Constructor.
	 */
	public SynopsisDaoImpl() {
		super(Synopsis.class);
	}

}
