package org.telosys.starterkits.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author slabbe
 *
 * @param <T>
 * @param <PK>
 */
@Transactional
public interface Service<T, PK extends Serializable> {

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