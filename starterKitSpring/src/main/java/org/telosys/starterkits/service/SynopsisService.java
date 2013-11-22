/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:56 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Synopsis;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.SynopsisPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


/**
 * Service : Synopsis.
 */
public class SynopsisService implements IService<Synopsis, Integer> {

	protected final Logger LOG = LoggerFactory.getLogger(SynopsisService.class);
	
	private SynopsisPersistence getSynopsisPersistence() {
		return PersistenceServiceProvider.getService(SynopsisPersistence.class);
	}

	public Synopsis load(final Integer bookId) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Synopsis synopsis;
		try {
			SynopsisPersistence synopsisPersistence = getSynopsisPersistence();
			synopsis = synopsisPersistence.load(bookId);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return synopsis ;
	}

	public Synopsis save(final Synopsis synopsis) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Synopsis synopsisSaved;
		try {
			SynopsisPersistence synopsisPersistence = getSynopsisPersistence();
			synopsisSaved = synopsisPersistence.save(synopsis);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return synopsisSaved;
	}

	public void delete(final Integer bookId) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			SynopsisPersistence synopsisPersistence = getSynopsisPersistence();
			synopsisPersistence.delete(bookId);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Synopsis> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Synopsis> synopsiss;
		try {
			SynopsisPersistence synopsisPersistence = getSynopsisPersistence();
			synopsiss = synopsisPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return synopsiss;
	}

	public List<Synopsis> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Synopsis> synopsiss;
		try {
			SynopsisPersistence synopsisPersistence = getSynopsisPersistence();
			synopsiss = synopsisPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return synopsiss;
	}

}
