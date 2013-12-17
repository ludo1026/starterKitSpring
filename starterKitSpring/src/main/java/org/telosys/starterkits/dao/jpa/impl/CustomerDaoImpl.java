package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Customer;
import org.telosys.starterkits.dao.jpa.CustomerDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Customer.
 */
@Component
public class CustomerDaoImpl extends DaoImpl<Customer, String> implements CustomerDao {

	/**
	 * Constructor.
	 */
	public CustomerDaoImpl() {
		super(Customer.class);
	}

}
