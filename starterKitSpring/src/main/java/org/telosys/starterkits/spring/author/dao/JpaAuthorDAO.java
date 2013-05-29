/*
 * D.A.O. for JPA  
 * Created on 22 mai 2013 ( Time 15:26:18 )
 */

package org.telosys.starterkits.spring.author.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.telosys.starterkits.spring.author.bean.Author;
import org.telosys.starterkits.springjpa.CoreUtils;
import org.telosys.starterkits.springjpa.GenericJpaDao;

public class JpaAuthorDAO extends GenericJpaDao<Author, Integer> {

	private static final Predicate[] ap = {};
	
	/**
	 * Constructs a DAO for Author
	 */
	public JpaAuthorDAO() {
		super(Author.class);
	}
	
	/**
	 * Search by Author example
	 */
	public List<Author> search(final Author element) {
		final EntityManager em = getEntityManager();
		if ( element != null  ) {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
			Root<Author> from = criteriaQuery.from(Author.class);

			List<Predicate> listP = new ArrayList<Predicate>();


			if (CoreUtils.isNotBlank(element.getId())) {
				Predicate predicate = criteriaBuilder.equal(from.get("id"), element.getId());
				listP.add(predicate);
			}
			if (CoreUtils.isNotBlank(element.getFirstName())) {
				Predicate predicate = criteriaBuilder.equal(from.get("firstName"), element.getFirstName());
				listP.add(predicate);
			}
			if (CoreUtils.isNotBlank(element.getLastName())) {
				Predicate predicate = criteriaBuilder.equal(from.get("lastName"), element.getLastName());
				listP.add(predicate);
			}
			
			criteriaQuery.where(listP.toArray(ap));
			TypedQuery<Author> tq = em.createQuery(criteriaQuery);
			return tq.getResultList();
		}
		else
		{
    		return this.loadAll();
		}
	}

}