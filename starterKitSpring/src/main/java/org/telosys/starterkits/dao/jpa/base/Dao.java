package org.telosys.starterkits.dao.jpa.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface Dao<T, PK extends java.io.Serializable> {

	/**
	 * Find entity by Primary Key
	 * 
	 * @param primaryKey
	 * @return
	 */
	T load(final PK primaryKey);

	/**
	 * Load all entities
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	List<T> loadAll();

	/**
	 * Load a list of entities using a named query without parameter
	 * 
	 * @param queryName
	 * @return
	 */
	List<T> loadByNamedQuery(final String queryName);

	/**
	 * Load a list of entities using a named query with parameters
	 * 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	List<T> loadByNamedQuery(final String queryName,
			final Map<String, Object> queryParameters);

	/**
	 * Insert entity ( TRANSACTIONAL )
	 * 
	 * @param <T>
	 * @return
	 */
	void insert(final T entity);

	/**
	 * Save the given entity ( TRANSACTIONAL )
	 * 
	 * @param <T>
	 * @param entity
	 * @return
	 */
	T save(final T entityToSave);

	/**
	 * Delete entity by primary key ( TRANSACTIONAL )
	 * 
	 * @param primaryKey
	 */
	boolean delete(final PK primaryKey);

	/**
	 * Search entities using the given query parameters <br>
	 * Returns all the entities if no query parameter
	 * 
	 * @param queryParameters
	 *            the query parameters to be used (can be null )
	 * @return
	 */
	List<T> search(final Map<String, Object> queryParameters);
	
}
