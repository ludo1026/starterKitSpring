   package org.telosys.starterkits.web;

import java.util.List;
import java.util.Date;

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

	void populateEditForm(Model uiModel, Review review) {
		uiModel.addAttribute("review", review);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("books", bookService.loadAll());
    	uiModel.addAttribute("customers", customerService.loadAll());
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
	public String save(@Valid Review review, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		review.setBookId(review.getBook().getId());
		review.setCustomerCode(review.getCustomer().getCode());
		if (!result.hasErrors()) {
			review = reviewService.save(review);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/review/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, review.getBookId(), review.getCustomerCode());
		} else {
			return "review/edit";
		}
	}

	@RequestMapping(value = "/{bookId}/{customerCode}")
	public String edit(Model uiModel, @PathVariable("bookId") Integer bookId, @PathVariable("customerCode") String customerCode) {
		ReviewId reviewid = new ReviewId();
		reviewid.setBookId(bookId);
		reviewid.setCustomerCode(customerCode);
		Review review = reviewService.load(reviewid);
		this.populateEditForm(uiModel, review);
		return "review/edit";
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
