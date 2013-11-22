/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:39 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.telosys.starterkits.bean.BookOrderItem;

import org.telosys.starterkits.bean.BookOrderItemId;
import org.telosys.starterkits.service.BookOrderItemService;

/**
 * BookOrderItem.
 */
@Controller
@RequestMapping("/bookorderitem*")
public class BookOrderItemController 
{
    private BookOrderItemService bookorderitemService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("bookorderitem/bookorderitem", "command", new  BookOrderItem());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showBookOrderItems() {
		bookorderitemService = new BookOrderItemService();
		ModelAndView mav = new ModelAndView("bookorderitem/bookorderitemList");
		List<BookOrderItem> list = bookorderitemService.loadAll();
		mav.addObject("listBookOrderItems", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookorderitemForm") BookOrderItem bookorderitem, BindingResult result) {
		bookorderitemService = new BookOrderItemService();
		if (!result.hasErrors()) {
			bookorderitemService.save(bookorderitem);
		}
		return "redirect:/bookorderitem/search";
	}

	@RequestMapping(value = "/edit/{bookOrderId}/{bookId}")
	public ModelAndView edit(@ModelAttribute("bookorderitem/edit") BookOrderItem bookorderitem, @PathVariable("bookOrderId") Integer bookOrderId, @PathVariable("bookId") Integer bookId) {
		bookorderitemService = new BookOrderItemService();
		ModelAndView modelAndView = new ModelAndView("bookorderitem/bookorderitem");

		BookOrderItemId id = new BookOrderItemId();
		id.setBookOrderId(bookOrderId);
		id.setBookId(bookId);

		BookOrderItem bookorderitemloaded = bookorderitemService.load(id);

		modelAndView.addObject("current", bookorderitemloaded);
		return modelAndView;
	}
}
