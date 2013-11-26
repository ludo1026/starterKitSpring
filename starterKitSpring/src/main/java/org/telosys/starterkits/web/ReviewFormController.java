/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:07:35 )
 */

package org.telosys.starterkits.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
@RequestMapping("/reviewForm")
public class ReviewFormController 
{
	@Resource
    private ReviewService reviewService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("reviewForm") Review review, BindingResult result) {
		if (!result.hasErrors()) {
			reviewService.save(review);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/review/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("review/delete") Review review, @PathVariable("id") ReviewId id) {
		if (id != null){
			reviewService.delete(id);
		}
		return "redirect:/review/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("reviewForm") Review review, BindingResult result) {
		ModelAndView mav = new ModelAndView("review/reviewList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Review> list = reviewService.search(criteria);
		mav.addObject("listreviews", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("review/review", "reviewForm", new Review());
	}
}
