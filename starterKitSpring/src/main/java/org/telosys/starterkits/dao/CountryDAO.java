/*
 * D.A.O. for JPA  
 * Created on 22 mai 2013 ( Time 15:26:18 )
 */

package org.telosys.starterkits.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.springjpa.CoreUtils;
import org.telosys.starterkits.springjpa.GenericJpaDao;


public class CountryDAO extends GenericJpaDao<Country, String> {

	private static final Predicate[] ap = {};
	
	/**
	 * Constructs a DAO for Country
	 */
	public CountryDAO() {
		super(Country.class);
	}
	
	/**
	 * Search by Country example
	 */
	public List<Country> search(final Country element) {
		final EntityManager em = getEntityManager();
		if ( element != null  ) {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
			Root<Country> from = criteriaQuery.from(Country.class);

			List<Predicate> listP = new ArrayList<Predicate>();

			if (CoreUtils.isNotBlank(element.getCode())) {
				Predicate predicate = criteriaBuilder.equal(from.get("code"), element.getCode());
				listP.add(predicate);
			}
			
			criteriaQuery.where(listP.toArray(ap));
			TypedQuery<Country> tq = em.createQuery(criteriaQuery);
			return tq.getResultList();
		}
		else
		{
    		return this.loadAll();
		}
	}

}