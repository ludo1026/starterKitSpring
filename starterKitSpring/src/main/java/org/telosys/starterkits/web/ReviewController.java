/*
 * Controller class 
 * Created on 27 nov. 2013 ( Time 18:10:07 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping("/review")
public class ReviewController 
{
	@Resource
    private ReviewService reviewService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Review review) {
		uiModel.addAttribute("review", review);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Review());
		return "review/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Review> list = reviewService.loadAll();
		uiModel.addAttribute("listReviews", list);
		return "review/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("reviewForm") Review review, BindingResult result) {
		if (!result.hasErrors()) {
			reviewService.save(review);
		}
		return "redirect:/review";
	}

	@RequestMapping(value = "/{customerCode}/{bookId}")
	public String edit(Model uiModel, @PathVariable("customerCode") String customerCode, @PathVariable("bookId") Integer bookId) {
		ReviewId id = new ReviewId();
		id.setCustomerCode(customerCode);
		id.setBookId(bookId);
		Review review = reviewService.load(id);
		this.populateEditForm(uiModel, review);
		return "review/edit";
	}

	@RequestMapping(value = "/delete/{customerCode}/{bookId}")
	public String delete(Model uiModel, @PathVariable("customerCode") String customerCode, @PathVariable("bookId") Integer bookId) {
		ReviewId id = new ReviewId();
		id.setCustomerCode(customerCode);
		id.setBookId(bookId);
		reviewService.delete(id);
		return "redirect:/review";
	}
	
}
