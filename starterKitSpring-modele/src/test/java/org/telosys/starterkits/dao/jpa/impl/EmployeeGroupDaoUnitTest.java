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
import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeGroupDaoUnitTest {

	@InjectMocks
	private EmployeeGroupDaoImpl employeegroupDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		EmployeeGroupId id = new EmployeeGroupId();

		EmployeeGroup employeegroup = new EmployeeGroup();
		employeegroup.setId(id);

		when(entityManager.find(EmployeeGroup.class, id)).thenReturn(employeegroup);

		// When
		EmployeeGroup employeegroupResult = employeegroupDao.load(id);

		// Then
		assertEquals(id, employeegroupResult.getId());
	}

	@Test
	public void save() {
		// Given
		EmployeeGroup employeegroupToSave = new EmployeeGroup();
		EmployeeGroup employeegroupSaved = new EmployeeGroup();

		when(entityManager.merge(employeegroupToSave)).thenReturn(employeegroupSaved);

		// When
		EmployeeGroup employeegroupResult = employeegroupDao.save(employeegroupToSave);

		// Then
		assertTrue(employeegroupResult != employeegroupToSave);
		assertTrue(employeegroupResult == employeegroupSaved);
	}

	@Test
	public void delete() {
		// Given
		EmployeeGroupId id = new EmployeeGroupId();

		EmployeeGroup employeegroup = new EmployeeGroup();
		when(entityManager.find(EmployeeGroup.class, id)).thenReturn(employeegroup);

		// When
		employeegroupDao.delete(id);

		// Then
		verify(entityManager).remove(employeegroup);
	}

	@Test
	public void loadAll() {
		// Given
		List<EmployeeGroup> employeegroups = new ArrayList<EmployeeGroup>();
		employeegroups.add(new EmployeeGroup());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + EmployeeGroup.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(employeegroups);

		// When
		List<EmployeeGroup> employeegroupsResult = employeegroupDao.loadAll();

		// Then
		assertTrue(employeegroupsResult == employeegroups);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<EmployeeGroup> employeegroups = new ArrayList<EmployeeGroup>();
		employeegroups.add(new EmployeeGroup());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + EmployeeGroup.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(employeegroups);

		// When
		List<EmployeeGroup> employeegroupsResult = employeegroupDao.search(null);

		// Then
		assertTrue(employeegroupsResult == employeegroups);
	}

}
