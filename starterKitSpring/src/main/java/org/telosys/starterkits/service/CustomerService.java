/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:42 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Customer;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.CustomerPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


/**
 * Service : Customer.
 */
public class CustomerService implements IService<Customer, String> {

	protected final Logger LOG = LoggerFactory.getLogger(CustomerService.class);
	
	private CustomerPersistence getCustomerPersistence() {
		return PersistenceServiceProvider.getService(CustomerPersistence.class);
	}

	public Customer load(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Customer customer;
		try {
			CustomerPersistence customerPersistence = getCustomerPersistence();
			customer = customerPersistence.load(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return customer ;
	}

	public Customer save(final Customer customer) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Customer customerSaved;
		try {
			CustomerPersistence customerPersistence = getCustomerPersistence();
			customerSaved = customerPersistence.save(customer);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return customerSaved;
	}

	public void delete(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			CustomerPersistence customerPersistence = getCustomerPersistence();
			customerPersistence.delete(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Customer> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Customer> customers;
		try {
			CustomerPersistence customerPersistence = getCustomerPersistence();
			customers = customerPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return customers;
	}

	public List<Customer> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Customer> customers;
		try {
			CustomerPersistence customerPersistence = getCustomerPersistence();
			customers = customerPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return customers;
	}

}
