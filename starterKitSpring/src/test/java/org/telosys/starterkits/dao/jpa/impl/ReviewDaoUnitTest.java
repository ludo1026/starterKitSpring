package org.telosys.starterkits.dao.jpa.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telosys.starterkits.bean.Review;
import org.telosys.starterkits.bean.ReviewId;

@RunWith(MockitoJUnitRunner.class)
public class ReviewDaoUnitTest {

	@InjectMocks
	private ReviewDaoImpl reviewDao;
	@Mock
	private EntityManager entityManager;

	@Test
	public void load() {
		// Given
		ReviewId id = new ReviewId();

		Review review = new Review();
		review.setId(id);

		when(entityManager.find(Review.class, id)).thenReturn(review);

		// When
		Review reviewResult = reviewDao.load(id);

		// Then
		assertEquals(id, reviewResult.getId());
	}

	@Test
	public void save() {
		// Given
		Review reviewToSave = new Review();
		Review reviewSaved = new Review();

		when(entityManager.merge(reviewToSave)).thenReturn(reviewSaved);

		// When
		Review reviewResult = reviewDao.save(reviewToSave);

		// Then
		assertTrue(reviewResult != reviewToSave);
		assertTrue(reviewResult == reviewSaved);
	}

	@Test
	public void delete() {
		// Given
		ReviewId id = new ReviewId();

		Review review = new Review();
		when(entityManager.find(Review.class, id)).thenReturn(review);

		// When
		reviewDao.delete(id);

		// Then
		verify(entityManager).remove(review);
	}

	@Test
	public void loadAll() {
		// Given
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Review.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(reviews);

		// When
		List<Review> reviewsResult = reviewDao.loadAll();

		// Then
		assertTrue(reviewsResult == reviews);
	}

	@Test
	public void search_without_criteria() {
		// Given
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review());

		Query query = mock(Query.class);
		when(entityManager.createQuery("from " + Review.class.getName()))
				.thenReturn(query);

		when(query.getResultList()).thenReturn(reviews);

		// When
		List<Review> reviewsResult = reviewDao.search(null);

		// Then
		assertTrue(reviewsResult == reviews);
	}

}
