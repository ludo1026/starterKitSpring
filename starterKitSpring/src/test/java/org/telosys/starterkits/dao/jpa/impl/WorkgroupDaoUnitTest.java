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
import org.telosys.starterkits.bean.Workgroup;

@RunWith(MockitoJUnitRunner.class)
public class WorkgroupDaoUnitTest {

	@InjectMocks
	private WorkgroupDaoImpl workgroupDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		Short id = Short.valueOf("1");

		Workgroup workgroup = new Workgroup();
		workgroup.setId(id);

		when(entityManager.find(Workgroup.class, id)).thenReturn(workgroup);

		// When
		Workgroup workgroupResult = workgroupDao.load(id);

		// Then
		assertEquals(id, workgroupResult.getId());
	}

	@Test
	public void save() {
		// Given
		Workgroup workgroupToSave = new Workgroup();
		Workgroup workgroupSaved = new Workgroup();

		when(entityManager.merge(workgroupToSave)).thenReturn(workgroupSaved);

		// When
		Workgroup workgroupResult = workgroupDao.save(workgroupToSave);

		// Then
		assertTrue(workgroupResult != workgroupToSave);
		assertTrue(workgroupResult == workgroupSaved);
	}

	@Test
	public void delete() {
		// Given
		Short id = Short.valueOf("1");

		Workgroup workgroup = new Workgroup();
		when(entityManager.find(Workgroup.class, id)).thenReturn(workgroup);

		// When
		workgroupDao.delete(id);

		// Then
		verify(entityManager).remove(workgroup);
	}

	@Test
	public void loadAll() {
		// Given
		List<Workgroup> workgroups = new ArrayList<Workgroup>();
		workgroups.add(new Workgroup());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Workgroup.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(workgroups);

		// When
		List<Workgroup> workgroupsResult = workgroupDao.loadAll();

		// Then
		assertTrue(workgroupsResult == workgroups);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Workgroup> workgroups = new ArrayList<Workgroup>();
		workgroups.add(new Workgroup());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Workgroup.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(workgroups);

		// When
		List<Workgroup> workgroupsResult = workgroupDao.search(null);

		// Then
		assertTrue(workgroupsResult == workgroups);
	}

}
