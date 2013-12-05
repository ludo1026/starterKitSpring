package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Badge;
import org.telosys.starterkits.dao.jpa.BadgeDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Badge.
 */
@Component
public class BadgeDaoImpl extends DaoImpl<Badge, Integer> implements BadgeDao {

	/**
	 * Constructor.
	 */
	public BadgeDaoImpl() {
		super(Badge.class);
	}

}
