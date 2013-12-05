package org.telosys.starterkits.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telosys.starterkits.bean.Customer;
import org.telosys.starterkits.dao.jpa.CustomerDao;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplUnitTest {
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	@Mock
	private CustomerDao customerDao;
	
	@Test
	public void load() {
		// Given
		String code = "test";

		Customer customer = new Customer();
		customer.setCode(code);
		
		when(customerDao.load(code)).thenReturn(customer);

		// When
		Customer customerResult = customerService.load(code);
		
		// Then
		assertEquals(code, customerResult.getCode());
	}
	
	@Test
	public void save() {
		// Given
		Customer customerToSave = new Customer();
		Customer customerSaved = new Customer();
		
		when(customerDao.save(customerToSave)).thenReturn(customerSaved);

		// When
		Customer customerResult = customerService.save(customerToSave);
		
		// Then
		assertTrue(customerResult != customerToSave);
		assertTrue(customerResult == customerSaved);
	}

	@Test
	public void delete() {
		// Given
		String code = "test";
		
		// When
		customerService.delete(code);
		
		// Then
		verify(customerDao).delete(code);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer());
		
		when(customerDao.search(criteria)).thenReturn(customers);
		
		// When
		List<Customer> customersResult = customerService.search(criteria);
		
		// Then
		assertTrue(customersResult == customers);
	}

	@Test
	public void loadAll() {
		// Given
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer());
		
		when(customerDao.loadAll()).thenReturn(customers);
		
		// When
		List<Customer> customersResult = customerService.loadAll();
		
		// Then
		assertTrue(customersResult == customers);
	}
	
}
