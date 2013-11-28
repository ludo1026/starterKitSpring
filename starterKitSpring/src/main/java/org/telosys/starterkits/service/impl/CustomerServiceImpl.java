package org.telosys.starterkits.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Customer;
import org.telosys.starterkits.dao.jpa.CustomerDao;
import org.telosys.starterkits.dao.repository.CustomerRepository;
import org.telosys.starterkits.service.CustomerService;

/**
 * Service : Customer.
 */
@Component
@Transactional
public class CustomerServiceImpl implements CustomerService {
		
	@Resource
	private CustomerDao customerDao;
	@Resource
	private CustomerRepository customerRepository;
	
	public Customer load(final String code) {
		return customerDao.load(code);
	}
	
	public Customer save(final Customer customer) {
		return customerDao.save(customer);
	}

	public void delete(final String code) {
		customerDao.delete(code);
	}

	public List<Customer> search(final Map<String,Object> criteria) {
		return customerDao.search(criteria);
	}

	public List<Customer> loadAll() {
		return customerDao.loadAll();
	}
	
}
