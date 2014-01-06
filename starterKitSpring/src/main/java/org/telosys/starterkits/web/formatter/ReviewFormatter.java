package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Review;

@Component
public class ReviewFormatter implements Formatter<Review> {

	@Override
	public String display(Review review) {
		return review.toString();
	}

}
