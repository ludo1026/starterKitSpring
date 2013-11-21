package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

/**
 * @author slabbe
 *
 * @param <T>
 * @param <PK>
 */
public interface IService<T, PK> {

	/**
	 * Load entity
	 * @param id
	 * @return entity
	 */
	T load(final PK id) ;

	/**
	 * Save entity
	 * @param entity
	 * @return entity
	 */
	T save(final T entity);

	/**
	 * Delete entity
	 * @param id
	 */
	void delete(final PK id);

	/**
	 * Search entity
	 * @param criteria
	 * @return list entities
	 */
	List<T> search(final Map<String,Object> criteria);

}