package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.BookOrder;
import org.telosys.starterkits.dao.jpa.BookOrderDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : BookOrder.
 */
@Component
public class BookOrderDaoImpl extends DaoImpl<BookOrder, Integer> implements BookOrderDao {

	/**
	 * Constructor.
	 */
	public BookOrderDaoImpl() {
		super(BookOrder.class);
	}

}
