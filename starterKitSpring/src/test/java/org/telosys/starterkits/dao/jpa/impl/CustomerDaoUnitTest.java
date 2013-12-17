package org.telosys.starterkits.dao.jpa.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telosys.starterkits.bean.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoUnitTest {

	@InjectMocks
	private CustomerDaoImpl customerDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		String code = "test";

		Customer customer = new Customer();
		customer.setCode(code);

		when(entityManager.find(Customer.class, code)).thenReturn(customer);

		// When
		Customer customerResult = customerDao.load(code);

		// Then
		assertEquals(code, customerResult.getCode());
	}

	@Test
	public void save() {
		// Given
		Customer customerToSave = new Customer();
		Customer customerSaved = new Customer();

		when(entityManager.merge(customerToSave)).thenReturn(customerSaved);

		// When
		Customer customerResult = customerDao.save(customerToSave);

		// Then
		assertTrue(customerResult != customerToSave);
		assertTrue(customerResult == customerSaved);
	}

	@Test
	public void delete() {
		// Given
		String code = "test";

		Customer customer = new Customer();
		when(entityManager.find(Customer.class, code)).thenReturn(customer);

		// When
		customerDao.delete(code);

		// Then
		verify(entityManager).remove(customer);
	}

	@Test
	public void loadAll() {
		// Given
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Customer.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(customers);

		// When
		List<Customer> customersResult = customerDao.loadAll();

		// Then
		assertTrue(customersResult == customers);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Customer.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(customers);

		// When
		List<Customer> customersResult = customerDao.search(null);

		// Then
		assertTrue(customersResult == customers);
	}

}
