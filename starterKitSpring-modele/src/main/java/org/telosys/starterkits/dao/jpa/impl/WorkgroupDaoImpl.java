package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Workgroup;
import org.telosys.starterkits.dao.jpa.WorkgroupDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Workgroup.
 */
@Component
public class WorkgroupDaoImpl extends DaoImpl<Workgroup, Short> implements WorkgroupDao {

	/**
	 * Constructor.
	 */
	public WorkgroupDaoImpl() {
		super(Workgroup.class);
	}

}
