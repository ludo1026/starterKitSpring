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
import org.telosys.starterkits.bean.Workgroup;
import org.telosys.starterkits.dao.jpa.WorkgroupDao;

@RunWith(MockitoJUnitRunner.class)
public class WorkgroupServiceImplUnitTest {
	
	@InjectMocks
	private WorkgroupServiceImpl workgroupService;
	@Mock
	private WorkgroupDao workgroupDao;
	
	@Test
	public void load() {
		// Given
		Short id = Short.valueOf("1");

		Workgroup workgroup = new Workgroup();
		workgroup.setId(id);
		
		when(workgroupDao.load(id)).thenReturn(workgroup);

		// When
		Workgroup workgroupResult = workgroupService.load(id);
		
		// Then
		assertEquals(id, workgroupResult.getId());
	}
	
	@Test
	public void save() {
		// Given
		Workgroup workgroupToSave = new Workgroup();
		Workgroup workgroupSaved = new Workgroup();
		
		when(workgroupDao.save(workgroupToSave)).thenReturn(workgroupSaved);

		// When
		Workgroup workgroupResult = workgroupService.save(workgroupToSave);
		
		// Then
		assertTrue(workgroupResult != workgroupToSave);
		assertTrue(workgroupResult == workgroupSaved);
	}

	@Test
	public void delete() {
		// Given
		Short id = Short.valueOf("1");
		
		// When
		workgroupService.delete(id);
		
		// Then
		verify(workgroupDao).delete(id);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Workgroup> workgroups = new ArrayList<Workgroup>();
		workgroups.add(new Workgroup());
		
		when(workgroupDao.search(criteria)).thenReturn(workgroups);
		
		// When
		List<Workgroup> workgroupsResult = workgroupService.search(criteria);
		
		// Then
		assertTrue(workgroupsResult == workgroups);
	}

	@Test
	public void loadAll() {
		// Given
		List<Workgroup> workgroups = new ArrayList<Workgroup>();
		workgroups.add(new Workgroup());
		
		when(workgroupDao.loadAll()).thenReturn(workgroups);
		
		// When
		List<Workgroup> workgroupsResult = workgroupService.loadAll();
		
		// Then
		assertTrue(workgroupsResult == workgroups);
	}
	
}
