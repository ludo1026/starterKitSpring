package org.telosys.starterkits.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.telosys.starterkits.bean.Book;

/**
 * Repository : Book.
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

}
