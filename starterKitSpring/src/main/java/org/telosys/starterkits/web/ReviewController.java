/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:07:35 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.telosys.starterkits.bean.Review;

import org.telosys.starterkits.bean.ReviewId;
import org.telosys.starterkits.service.ReviewService;

/**
 * Review.
 */
@Controller
@RequestMapping("/review*")
public class ReviewController 
{
	@Resource
    private ReviewService reviewService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("review/review", "reviewForm", new  Review());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showReviews() {
		ModelAndView mav = new ModelAndView("review/reviewList");
		List<Review> list = reviewService.loadAll();
		mav.addObject("listReviews", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("reviewForm") Review review, BindingResult result) {
		if (!result.hasErrors()) {
			reviewService.save(review);
		}
		return "redirect:/review/search";
	}

	@RequestMapping(value = "/edit/{customerCode}/{bookId}")
	public ModelAndView edit(@ModelAttribute("review/edit") Review review, @PathVariable("customerCode") String customerCode, @PathVariable("bookId") Integer bookId) {
		ModelAndView modelAndView = new ModelAndView("review/review");

		ReviewId id = new ReviewId();
		id.setCustomerCode(customerCode);
		id.setBookId(bookId);

		Review reviewloaded = reviewService.load(id);

		modelAndView.addObject("reviewForm", reviewloaded);
		return modelAndView;
	}
}
