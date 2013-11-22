/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:56 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Workgroup;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.WorkgroupPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


/**
 * Service : Workgroup.
 */
public class WorkgroupService implements IService<Workgroup, Short> {

	protected final Logger LOG = LoggerFactory.getLogger(WorkgroupService.class);
	
	private WorkgroupPersistence getWorkgroupPersistence() {
		return PersistenceServiceProvider.getService(WorkgroupPersistence.class);
	}

	public Workgroup load(final Short id) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Workgroup workgroup;
		try {
			WorkgroupPersistence workgroupPersistence = getWorkgroupPersistence();
			workgroup = workgroupPersistence.load(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return workgroup ;
	}

	public Workgroup save(final Workgroup workgroup) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Workgroup workgroupSaved;
		try {
			WorkgroupPersistence workgroupPersistence = getWorkgroupPersistence();
			workgroupSaved = workgroupPersistence.save(workgroup);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return workgroupSaved;
	}

	public void delete(final Short id) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			WorkgroupPersistence workgroupPersistence = getWorkgroupPersistence();
			workgroupPersistence.delete(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Workgroup> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Workgroup> workgroups;
		try {
			WorkgroupPersistence workgroupPersistence = getWorkgroupPersistence();
			workgroups = workgroupPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return workgroups;
	}

	public List<Workgroup> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Workgroup> workgroups;
		try {
			WorkgroupPersistence workgroupPersistence = getWorkgroupPersistence();
			workgroups = workgroupPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return workgroups;
	}

}
