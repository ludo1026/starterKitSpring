/*
 * Created on 22 nov. 2013 ( Time 16:27:33 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */

package org.telosys.starterkits.persistence.services.jpa;


import org.telosys.starterkits.bean.Badge ;
import org.telosys.starterkits.persistence.commons.jpa.GenericJpaService;
import org.telosys.starterkits.persistence.services.BadgePersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Badge" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class BadgePersistenceJPA extends GenericJpaService<Badge, Integer> implements BadgePersistence {

	/**
	 * Constructor
	 */
	public BadgePersistenceJPA() {
		super(Badge.class);
	}

	/**
	 * Delete badge.
	 */
	public boolean delete(Badge badge) {
		return delete(badge.getBadgeNumber());
	}

}
