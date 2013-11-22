/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:33 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Badge;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.BadgePersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


/**
 * Service : Badge.
 */
public class BadgeService implements IService<Badge, Integer> {

	protected final Logger LOG = LoggerFactory.getLogger(BadgeService.class);
	
	private BadgePersistence getBadgePersistence() {
		return PersistenceServiceProvider.getService(BadgePersistence.class);
	}

	public Badge load(final Integer badgeNumber) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Badge badge;
		try {
			BadgePersistence badgePersistence = getBadgePersistence();
			badge = badgePersistence.load(badgeNumber);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return badge ;
	}

	public Badge save(final Badge badge) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Badge badgeSaved;
		try {
			BadgePersistence badgePersistence = getBadgePersistence();
			badgeSaved = badgePersistence.save(badge);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return badgeSaved;
	}

	public void delete(final Integer badgeNumber) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			BadgePersistence badgePersistence = getBadgePersistence();
			badgePersistence.delete(badgeNumber);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Badge> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Badge> badges;
		try {
			BadgePersistence badgePersistence = getBadgePersistence();
			badges = badgePersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return badges;
	}

	public List<Badge> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Badge> badges;
		try {
			BadgePersistence badgePersistence = getBadgePersistence();
			badges = badgePersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return badges;
	}

}
