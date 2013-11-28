package org.telosys.starterkits.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.telosys.starterkits.bean.BookOrder;

/**
 * Repository : BookOrder.
 */
public interface BookOrderRepository extends PagingAndSortingRepository<BookOrder, Integer> {

}
