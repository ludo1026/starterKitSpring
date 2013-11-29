package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Review;
import org.telosys.starterkits.bean.ReviewId;
import org.telosys.starterkits.dao.jpa.ReviewDao;
import org.telosys.starterkits.dao.repository.ReviewRepository;
import org.telosys.starterkits.service.ReviewService;

/**
 * Service : Review.
 */
@Component
@Transactional
public class ReviewServiceImpl implements ReviewService {
		
	@Resource
	private ReviewDao reviewDao;
	@Resource
	private ReviewRepository reviewRepository;
	
	public Review load(final ReviewId id) {
		return reviewRepository.findOne(id);
	}
	
	public Review save(final Review review) {
		return reviewRepository.save(review);
	}

	public void delete(final ReviewId id) {
		reviewRepository.delete(id);
	}

	public List<Review> search(final Map<String,Object> criteria) {
		return reviewDao.search(criteria);
	}

	public List<Review> loadAll() {
		List<Review> reviews = new ArrayList<Review>();
		for (Review review : reviewRepository.findAll()) {
			reviews.add(review);
		}
		return reviews;
	}
	
	@Transactional(readOnly=true)
	public Page<Review> findAllByPage(Pageable pageable) {
		return reviewRepository.findAll(pageable);
	}
	
}
