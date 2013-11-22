/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:51 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Review;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.ReviewPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import org.telosys.starterkits.bean.ReviewId;

/**
 * Service : Review.
 */
public class ReviewService implements IService<Review, ReviewId> {

	protected final Logger LOG = LoggerFactory.getLogger(ReviewService.class);
	
	private ReviewPersistence getReviewPersistence() {
		return PersistenceServiceProvider.getService(ReviewPersistence.class);
	}

	public Review load(final ReviewId id) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Review review;
		try {
			ReviewPersistence reviewPersistence = getReviewPersistence();
			review = reviewPersistence.load(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return review ;
	}

	public Review save(final Review review) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Review reviewSaved;
		try {
			ReviewPersistence reviewPersistence = getReviewPersistence();
			reviewSaved = reviewPersistence.save(review);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return reviewSaved;
	}

	public void delete(final ReviewId id) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			ReviewPersistence reviewPersistence = getReviewPersistence();
			reviewPersistence.delete(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Review> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Review> reviews;
		try {
			ReviewPersistence reviewPersistence = getReviewPersistence();
			reviews = reviewPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return reviews;
	}

	public List<Review> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Review> reviews;
		try {
			ReviewPersistence reviewPersistence = getReviewPersistence();
			reviews = reviewPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return reviews;
	}

}
