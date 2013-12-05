package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return customerRepository.findOne(code);
	}
	
	public Customer save(final Customer customer) {
		return customerRepository.save(customer);
	}

	public void delete(final String code) {
		customerRepository.delete(code);
	}

	public List<Customer> search(final Map<String,Object> criteria) {
		return customerDao.search(criteria);
	}

	public List<Customer> loadAll() {
		List<Customer> customers = new ArrayList<Customer>();
		for (Customer customer : customerRepository.findAll()) {
			customers.add(customer);
		}
		return customers;
	}
	
	@Transactional(readOnly=true)
	public Page<Customer> findAllByPage(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
	
}
