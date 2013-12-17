package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.dao.jpa.AuthorDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Author.
 */
@Component
public class AuthorDaoImpl extends DaoImpl<Author, Integer> implements AuthorDao {

	/**
	 * Constructor.
	 */
	public AuthorDaoImpl() {
		super(Author.class);
	}

}
