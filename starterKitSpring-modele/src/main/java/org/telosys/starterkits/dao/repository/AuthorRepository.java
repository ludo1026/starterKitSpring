package org.telosys.starterkits.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.telosys.starterkits.bean.Author;

/**
 * Repository : Author.
 */
public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {

}
