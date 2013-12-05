package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
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
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Review.
 */
@Controller
@RequestMapping("/review")
public class ReviewController 
{
	@Resource
    private ReviewService reviewService;
	@Resource
	private ControllerHelper controllerHelper;

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
	public String save(@Valid Review review, BindingResult result, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			review = reviewService.save(review);
			return "redirect:/review/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, review.getId().getCustomerCode(), review.getId().getBookId());
		} else {
			return "review/edit";
		}
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
