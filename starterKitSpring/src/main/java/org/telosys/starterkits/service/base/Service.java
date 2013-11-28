package org.telosys.starterkits.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Service.
 *
 * @param <T> Entity
 * @param <ID> Identifier
 */
public interface Service<T extends Serializable, ID extends Serializable> {

	/**
	 * Load all entities.
	 * @return all entities
	 */
	List<T> loadAll();

	/**
	 * Load entity
	 * @param id
	 * @return entity
	 */
	T load(final ID id) ;

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
	void delete(final ID id);

	/**
	 * Search entity
	 * @param criteria
	 * @return list entities
	 */
	List<T> search(final Map<String,Object> criteria);

}