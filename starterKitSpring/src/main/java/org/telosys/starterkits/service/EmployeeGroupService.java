/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:48 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.EmployeeGroup;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.EmployeeGroupPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import org.telosys.starterkits.bean.EmployeeGroupId;

/**
 * Service : EmployeeGroup.
 */
public class EmployeeGroupService implements IService<EmployeeGroup, EmployeeGroupId> {

	protected final Logger LOG = LoggerFactory.getLogger(EmployeeGroupService.class);
	
	private EmployeeGroupPersistence getEmployeeGroupPersistence() {
		return PersistenceServiceProvider.getService(EmployeeGroupPersistence.class);
	}

	public EmployeeGroup load(final EmployeeGroupId id) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		EmployeeGroup employeegroup;
		try {
			EmployeeGroupPersistence employeegroupPersistence = getEmployeeGroupPersistence();
			employeegroup = employeegroupPersistence.load(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return employeegroup ;
	}

	public EmployeeGroup save(final EmployeeGroup employeegroup) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		EmployeeGroup employeegroupSaved;
		try {
			EmployeeGroupPersistence employeegroupPersistence = getEmployeeGroupPersistence();
			employeegroupSaved = employeegroupPersistence.save(employeegroup);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return employeegroupSaved;
	}

	public void delete(final EmployeeGroupId id) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			EmployeeGroupPersistence employeegroupPersistence = getEmployeeGroupPersistence();
			employeegroupPersistence.delete(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<EmployeeGroup> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<EmployeeGroup> employeegroups;
		try {
			EmployeeGroupPersistence employeegroupPersistence = getEmployeeGroupPersistence();
			employeegroups = employeegroupPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return employeegroups;
	}

	public List<EmployeeGroup> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<EmployeeGroup> employeegroups;
		try {
			EmployeeGroupPersistence employeegroupPersistence = getEmployeeGroupPersistence();
			employeegroups = employeegroupPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return employeegroups;
	}

}
