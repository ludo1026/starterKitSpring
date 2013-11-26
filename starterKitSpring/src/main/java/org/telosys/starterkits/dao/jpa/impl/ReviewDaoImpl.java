package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Review;
import org.telosys.starterkits.bean.ReviewId;
import org.telosys.starterkits.dao.jpa.ReviewDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Review.
 */
@Component
public class ReviewDaoImpl extends DaoImpl<Review, ReviewId> implements ReviewDao {

	/**
	 * Constructor.
	 */
	public ReviewDaoImpl() {
		super(Review.class);
	}

}
