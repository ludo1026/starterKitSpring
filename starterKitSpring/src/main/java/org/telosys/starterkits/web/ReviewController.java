package org.telosys.starterkits.web;
   
import java.util.List;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.Review;
import org.telosys.starterkits.bean.ReviewId;
import org.telosys.starterkits.service.ReviewService;
import org.telosys.starterkits.service.BookService;
import org.telosys.starterkits.service.CustomerService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
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
	@Resource
    private BookService bookService;
	@Resource
    private CustomerService customerService;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Review> list = reviewService.loadAll();
		uiModel.addAttribute("listReviews", list);
		return "review/list";
	}

	void populateForm(Model uiModel, Review review) {
		uiModel.addAttribute("review", review);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("books", bookService.loadAll());
    	uiModel.addAttribute("customers", customerService.loadAll());
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Review());
		return "review/create";
	}

	@RequestMapping(value = "/{bookId}/{customerCode}")
	public String displayEditForm(Model uiModel, @PathVariable("bookId") Integer bookId, @PathVariable("customerCode") String customerCode) {
		ReviewId reviewid = new ReviewId();
		reviewid.setBookId(bookId);
		reviewid.setCustomerCode(customerCode);
		Review review = reviewService.load(reviewid);
		this.populateForm(uiModel, review);
		return "review/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Review review, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			if(reviewService.load(review.getId()) != null) {
				result.addError(new ObjectError("review", "already.exists"));
			}
		}
		if (!result.hasErrors()) {
			review = reviewService.save(review);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/review/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, review.getBookId(), review.getCustomerCode());
		} else {
			populateForm(uiModel, review);
			return "review/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Review review, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			review = reviewService.save(review);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/review/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, review.getBookId(), review.getCustomerCode());
		} else {
			populateForm(uiModel, review);
			return "review/edit";
		}
	}

	@RequestMapping(value = "/delete/{bookId}/{customerCode}")
	public String delete(Model uiModel, @PathVariable("bookId") Integer bookId, @PathVariable("customerCode") String customerCode) {
		ReviewId reviewid = new ReviewId();
		reviewid.setBookId(bookId);
		reviewid.setCustomerCode(customerCode);
		reviewService.delete(reviewid);
		return "redirect:/review";
	}
	
}
