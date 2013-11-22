/*
 * Created on 22 nov. 2013 ( Time 16:27:44 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */

package org.telosys.starterkits.persistence.services.jpa;


import org.telosys.starterkits.bean.Customer ;
import org.telosys.starterkits.persistence.commons.jpa.GenericJpaService;
import org.telosys.starterkits.persistence.services.CustomerPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Customer" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class CustomerPersistenceJPA extends GenericJpaService<Customer, String> implements CustomerPersistence {

	/**
	 * Constructor
	 */
	public CustomerPersistenceJPA() {
		super(Customer.class);
	}

	/**
	 * Delete customer.
	 */
	public boolean delete(Customer customer) {
		return delete(customer.getCode());
	}

}
