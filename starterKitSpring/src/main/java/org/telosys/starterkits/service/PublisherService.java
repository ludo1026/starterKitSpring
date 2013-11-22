/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:50 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Publisher;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.PublisherPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


/**
 * Service : Publisher.
 */
public class PublisherService implements IService<Publisher, Integer> {

	protected final Logger LOG = LoggerFactory.getLogger(PublisherService.class);
	
	private PublisherPersistence getPublisherPersistence() {
		return PersistenceServiceProvider.getService(PublisherPersistence.class);
	}

	public Publisher load(final Integer code) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Publisher publisher;
		try {
			PublisherPersistence publisherPersistence = getPublisherPersistence();
			publisher = publisherPersistence.load(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return publisher ;
	}

	public Publisher save(final Publisher publisher) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Publisher publisherSaved;
		try {
			PublisherPersistence publisherPersistence = getPublisherPersistence();
			publisherSaved = publisherPersistence.save(publisher);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return publisherSaved;
	}

	public void delete(final Integer code) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			PublisherPersistence publisherPersistence = getPublisherPersistence();
			publisherPersistence.delete(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Publisher> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Publisher> publishers;
		try {
			PublisherPersistence publisherPersistence = getPublisherPersistence();
			publishers = publisherPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return publishers;
	}

	public List<Publisher> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Publisher> publishers;
		try {
			PublisherPersistence publisherPersistence = getPublisherPersistence();
			publishers = publisherPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return publishers;
	}

}
