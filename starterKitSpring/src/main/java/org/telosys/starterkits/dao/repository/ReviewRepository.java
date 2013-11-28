package org.telosys.starterkits.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.telosys.starterkits.bean.Review;
import org.telosys.starterkits.bean.ReviewId;

/**
 * Repository : Review.
 */
public interface ReviewRepository extends PagingAndSortingRepository<Review, ReviewId> {

}
