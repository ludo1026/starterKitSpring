/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:06:38 )
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
	@Resource
    private BookOrderItemService bookorderitemService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("bookorderitem/bookorderitem", "bookorderitemForm", new  BookOrderItem());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showBookOrderItems() {
		ModelAndView mav = new ModelAndView("bookorderitem/bookorderitemList");
		List<BookOrderItem> list = bookorderitemService.loadAll();
		mav.addObject("listBookOrderItems", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookorderitemForm") BookOrderItem bookorderitem, BindingResult result) {
		if (!result.hasErrors()) {
			bookorderitemService.save(bookorderitem);
		}
		return "redirect:/bookorderitem/search";
	}

	@RequestMapping(value = "/edit/{bookOrderId}/{bookId}")
	public ModelAndView edit(@ModelAttribute("bookorderitem/edit") BookOrderItem bookorderitem, @PathVariable("bookOrderId") Integer bookOrderId, @PathVariable("bookId") Integer bookId) {
		ModelAndView modelAndView = new ModelAndView("bookorderitem/bookorderitem");

		BookOrderItemId id = new BookOrderItemId();
		id.setBookOrderId(bookOrderId);
		id.setBookId(bookId);

		BookOrderItem bookorderitemloaded = bookorderitemService.load(id);

		modelAndView.addObject("bookorderitemForm", bookorderitemloaded);
		return modelAndView;
	}
}
