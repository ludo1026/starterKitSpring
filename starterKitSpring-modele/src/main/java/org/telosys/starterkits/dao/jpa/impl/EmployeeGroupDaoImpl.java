package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;
import org.telosys.starterkits.dao.jpa.EmployeeGroupDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : EmployeeGroup.
 */
@Component
public class EmployeeGroupDaoImpl extends DaoImpl<EmployeeGroup, EmployeeGroupId> implements EmployeeGroupDao {

	/**
	 * Constructor.
	 */
	public EmployeeGroupDaoImpl() {
		super(EmployeeGroup.class);
	}

}
