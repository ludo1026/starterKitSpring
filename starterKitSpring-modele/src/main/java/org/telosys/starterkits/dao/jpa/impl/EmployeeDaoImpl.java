package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Employee;
import org.telosys.starterkits.dao.jpa.EmployeeDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Employee.
 */
@Component
public class EmployeeDaoImpl extends DaoImpl<Employee, String> implements EmployeeDao {

	/**
	 * Constructor.
	 */
	public EmployeeDaoImpl() {
		super(Employee.class);
	}

}
