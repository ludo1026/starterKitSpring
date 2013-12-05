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
import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;
import org.telosys.starterkits.dao.jpa.EmployeeGroupDao;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeGroupServiceImplUnitTest {
	
	@InjectMocks
	private EmployeeGroupServiceImpl employeegroupService;
	@Mock
	private EmployeeGroupDao employeegroupDao;
	
	@Test
	public void load() {
		// Given
		EmployeeGroupId id = new EmployeeGroupId();

		EmployeeGroup employeegroup = new EmployeeGroup();
		employeegroup.setId(id);
		
		when(employeegroupDao.load(id)).thenReturn(employeegroup);

		// When
		EmployeeGroup employeegroupResult = employeegroupService.load(id);
		
		// Then
		assertEquals(id, employeegroupResult.getId());
	}
	
	@Test
	public void save() {
		// Given
		EmployeeGroup employeegroupToSave = new EmployeeGroup();
		EmployeeGroup employeegroupSaved = new EmployeeGroup();
		
		when(employeegroupDao.save(employeegroupToSave)).thenReturn(employeegroupSaved);

		// When
		EmployeeGroup employeegroupResult = employeegroupService.save(employeegroupToSave);
		
		// Then
		assertTrue(employeegroupResult != employeegroupToSave);
		assertTrue(employeegroupResult == employeegroupSaved);
	}

	@Test
	public void delete() {
		// Given
		EmployeeGroupId id = new EmployeeGroupId();
		
		// When
		employeegroupService.delete(id);
		
		// Then
		verify(employeegroupDao).delete(id);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<EmployeeGroup> employeegroups = new ArrayList<EmployeeGroup>();
		employeegroups.add(new EmployeeGroup());
		
		when(employeegroupDao.search(criteria)).thenReturn(employeegroups);
		
		// When
		List<EmployeeGroup> employeegroupsResult = employeegroupService.search(criteria);
		
		// Then
		assertTrue(employeegroupsResult == employeegroups);
	}

	@Test
	public void loadAll() {
		// Given
		List<EmployeeGroup> employeegroups = new ArrayList<EmployeeGroup>();
		employeegroups.add(new EmployeeGroup());
		
		when(employeegroupDao.loadAll()).thenReturn(employeegroups);
		
		// When
		List<EmployeeGroup> employeegroupsResult = employeegroupService.loadAll();
		
		// Then
		assertTrue(employeegroupsResult == employeegroups);
	}
	
}
