package org.telosys.starterkits.dao.jpa.impl.base;

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

import org.telosys.starterkits.dao.jpa.base.Dao;

@Transactional
public class DaoImpl<T, ID extends java.io.Serializable> implements Dao<T, ID> {

	private static final Predicate[] VOID_PREDICATE_ARRAY = {};
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * The class of the entity to persist.
	 */
	private final Class<T> persistentClass;

	/**
	 * Constructor
	 * @param persistentClass
	 */
	public DaoImpl(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * Find entity by Primary Key
	 * 
	 * @param id
	 * @return
	 */
	public T load(final ID id) {
		return entityManager.find(persistentClass, id);
	}

	/**
	 * Load all entities
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public List<T> loadAll() {
		final Query query = entityManager.createQuery("from "
				+ persistentClass.getName());
		return query.getResultList();
	}

	/**
	 * Load a list of entities using a named query without parameter
	 * 
	 * @param queryName
	 * @return
	 */
	public List<T> loadByNamedQuery(final String queryName) {
		final Query query = entityManager.createNamedQuery(queryName);
		return query.getResultList();
	}

	/**
	 * Load a list of entities using a named query with parameters
	 * 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<T> loadByNamedQuery(final String queryName,
			final Map<String, Object> queryParameters) {
		final Query query = entityManager.createNamedQuery(queryName);
		final Iterator<String> i = queryParameters.keySet().iterator();
		while (i.hasNext()) {
			String key = i.next();
			query.setParameter(key, queryParameters.get(key));
		}
		return query.getResultList();
	}

	/**
	 * Insert entity ( TRANSACTIONAL )
	 * 
	 * @param <T>
	 * @return
	 */
	public void insert(final T entity) {
		entityManager.persist(entity);
	}

	/**
	 * Save the given entity ( TRANSACTIONAL )
	 * 
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public T save(final T entityToSave) {
		T managedEntity = entityManager.merge(entityToSave);
		return managedEntity;
	}

	/**
	 * Delete entity by primary key ( TRANSACTIONAL )
	 * 
	 * @param id
	 */
	public boolean delete(final ID id) {
		final T entity = entityManager.find(persistentClass, id);
		if (entity != null) {
			entityManager.remove(entity);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Search entities using the given query parameters <br>
	 * Returns all the entities if no query parameter
	 * 
	 * @param queryParameters
	 *            the query parameters to be used (can be null )
	 * @return
	 */
	public List<T> search(final Map<String, Object> queryParameters) {
		if (queryParameters != null) {
			return this.searchWithParameters(queryParameters);
		} else {
			return this.loadAll();
		}
	}

	/**
	 * Search entities using given query parameters
	 * 
	 * @param queryParameters
	 *            the query parameters to be used (cannot be null )
	 * @return
	 */
	private List<T> searchWithParameters(
			final Map<String, Object> queryParameters) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(persistentClass);
		Root<T> from = criteriaQuery.from(persistentClass);

		List<Predicate> predicates = new ArrayList<Predicate>();

		for (Map.Entry<String, Object> e : queryParameters.entrySet()) {
			String expression = e.getKey();
			Object value = e.getValue();

			if (value != null) {
				boolean operatorFound = false;
				int i = expression.indexOf(' ');
				if (i >= 0) {
					String name = expression.substring(0, i);
					String oper = expression.substring(i, expression.length())
							.trim();
					if (oper.length() > 0) {
						operatorFound = true;
						if (value instanceof String) {
							String strValue = (String) value;
							if ("=".equalsIgnoreCase(oper)) {
								Predicate p = criteriaBuilder.equal(
										from.get(name), strValue);
								predicates.add(p);
							} else if ("like".equalsIgnoreCase(oper)) {
								Predicate p = criteriaBuilder.like(
										from.<String> get(name), strValue);
								predicates.add(p);
							} else {
								throw new RuntimeException(
										"Search : invalid operator '" + oper
												+ "' for String attribute");
							}
						} else if (value instanceof Number) {
							Number numValue = (Number) value;
							if ("=".equalsIgnoreCase(oper)) {
								Predicate p = criteriaBuilder.equal(
										from.<Number> get(name), numValue);
								predicates.add(p);
							} else if ("!=".equalsIgnoreCase(oper)
									|| "<>".equalsIgnoreCase(oper)) {
								Predicate p = criteriaBuilder.notEqual(
										from.<Number> get(name), numValue);
								predicates.add(p);
							} else if (">".equalsIgnoreCase(oper)) {
								Predicate p = criteriaBuilder.gt(
										from.<Number> get(name), numValue);
								predicates.add(p);
							} else if ("<".equalsIgnoreCase(oper)) {
								Predicate p = criteriaBuilder.lt(
										from.<Number> get(name), numValue);
								predicates.add(p);
							} else if (">=".equalsIgnoreCase(oper)) {
								Predicate p = criteriaBuilder.ge(
										from.<Number> get(name), numValue);
								predicates.add(p);
							} else if ("<=".equalsIgnoreCase(oper)) {
								Predicate p = criteriaBuilder.le(
										from.<Number> get(name), numValue);
								predicates.add(p);
							} else {
								throw new RuntimeException(
										"Search : invalid operator '" + oper
												+ "' for Number attribute");
							}
						}
					}
				}
				if (!operatorFound) {
					predicates.add(criteriaBuilder.equal(from.get(expression),
							value));
				}
			}
		}

		criteriaQuery.where(predicates.toArray(VOID_PREDICATE_ARRAY));

		TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
	
}
