package org.telosys.starterkits.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.telosys.starterkits.bean.Review;
import org.telosys.starterkits.bean.ReviewId;

import org.telosys.starterkits.test.common.AbstractRealDBTest;

public class ReviewServiceRealDBIntegTest extends AbstractRealDBTest {

	@Override
	protected String getDataFilename() {
		return null;
	}

	@Resource
	private ReviewService reviewService;

	@Test
	public void cycle_vie_complet() {

		ReviewId id = new ReviewId();

		Review review = new Review();
		review.setId(id);
		//review.setName("Test " + id);

		// Create
		this.reviewService.save(review);

		// Search
		review = this.reviewService.load(id);
		Assert.assertNotNull(review);

		// Update
		//review.setName("Test 2 " + id);
		review = this.reviewService.save(review);

		// Search
		review = this.reviewService.load(id);
		//Assert.assertEquals("Test 2 " + id, review.getName());

		// Delete
		this.reviewService.delete(review.getId());

		// Search
		review = this.reviewService.load(id);
		Assert.assertNull(review);
	}

}
