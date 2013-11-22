/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:51 )
 */

package org.telosys.starterkits.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/reviewform")
public class ReviewFormController 
{
    private ReviewService reviewService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("reviewform") Review review, BindingResult result) {
		reviewService = new ReviewService();
		if (!result.hasErrors()) {
			reviewService.save(review);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/review/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("review/delete") Review review, @PathVariable("id") ReviewId id) {
		reviewService = new ReviewService();
		if (id != null){
			reviewService.delete(id);
		}
		return "redirect:/review/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("reviewform") Review review, BindingResult result) {
		reviewService = new ReviewService();
		ModelAndView mav = new ModelAndView("review/reviewList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Review> list = reviewService.search(criteria);
		mav.addObject("listreviews", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("review/review", "command", new Review());
	}
}
