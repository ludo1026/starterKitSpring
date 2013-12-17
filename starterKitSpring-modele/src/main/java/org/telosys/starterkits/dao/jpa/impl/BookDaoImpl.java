package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Book;
import org.telosys.starterkits.dao.jpa.BookDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Book.
 */
@Component
public class BookDaoImpl extends DaoImpl<Book, Integer> implements BookDao {

	/**
	 * Constructor.
	 */
	public BookDaoImpl() {
		super(Book.class);
	}

}
