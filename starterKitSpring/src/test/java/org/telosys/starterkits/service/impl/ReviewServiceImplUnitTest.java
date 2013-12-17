package org.telosys.starterkits.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telosys.starterkits.bean.Review;
import org.telosys.starterkits.bean.ReviewId;
import org.telosys.starterkits.dao.jpa.ReviewDao;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceImplUnitTest {
	
	@InjectMocks
	private ReviewServiceImpl reviewService;
	@Mock
	private ReviewDao reviewDao;
	
	@Test
	public void load() {
		// Given
		ReviewId id = new ReviewId();

		Review review = new Review();
		review.setId(id);
		
		when(reviewDao.load(id)).thenReturn(review);

		// When
		Review reviewResult = reviewService.load(id);
		
		// Then
		assertEquals(id, reviewResult.getId());
	}
	
	@Test
	public void save() {
		// Given
		Review reviewToSave = new Review();
		Review reviewSaved = new Review();
		
		when(reviewDao.save(reviewToSave)).thenReturn(reviewSaved);

		// When
		Review reviewResult = reviewService.save(reviewToSave);
		
		// Then
		assertTrue(reviewResult != reviewToSave);
		assertTrue(reviewResult == reviewSaved);
	}

	@Test
	public void delete() {
		// Given
		ReviewId id = new ReviewId();
		
		// When
		reviewService.delete(id);
		
		// Then
		verify(reviewDao).delete(id);
	}

	@Test
	public void search() {
		// Given
		Map<String,Object> criteria = new HashMap<String,Object>();
		
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review());
		
		when(reviewDao.search(criteria)).thenReturn(reviews);
		
		// When
		List<Review> reviewsResult = reviewService.search(criteria);
		
		// Then
		assertTrue(reviewsResult == reviews);
	}

	@Test
	public void loadAll() {
		// Given
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review());
		
		when(reviewDao.loadAll()).thenReturn(reviews);
		
		// When
		List<Review> reviewsResult = reviewService.loadAll();
		
		// Then
		assertTrue(reviewsResult == reviews);
	}
	
}
