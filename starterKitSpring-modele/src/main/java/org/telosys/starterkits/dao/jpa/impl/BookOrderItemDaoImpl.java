package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;
import org.telosys.starterkits.dao.jpa.BookOrderItemDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : BookOrderItem.
 */
@Component
public class BookOrderItemDaoImpl extends DaoImpl<BookOrderItem, BookOrderItemId> implements BookOrderItemDao {

	/**
	 * Constructor.
	 */
	public BookOrderItemDaoImpl() {
		super(BookOrderItem.class);
	}

}
