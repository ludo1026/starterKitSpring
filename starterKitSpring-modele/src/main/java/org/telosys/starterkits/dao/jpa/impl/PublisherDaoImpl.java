package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.dao.jpa.PublisherDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Publisher.
 */
@Component
public class PublisherDaoImpl extends DaoImpl<Publisher, Integer> implements PublisherDao {

	/**
	 * Constructor.
	 */
	public PublisherDaoImpl() {
		super(Publisher.class);
	}

}
