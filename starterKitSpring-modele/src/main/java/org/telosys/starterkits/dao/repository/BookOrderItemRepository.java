package org.telosys.starterkits.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;

/**
 * Repository : BookOrderItem.
 */
public interface BookOrderItemRepository extends PagingAndSortingRepository<BookOrderItem, BookOrderItemId> {

}
