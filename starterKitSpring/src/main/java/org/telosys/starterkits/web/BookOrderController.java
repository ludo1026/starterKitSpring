/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:06:23 )
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

import org.telosys.starterkits.bean.BookOrder;

import org.telosys.starterkits.service.BookOrderService;

/**
 * BookOrder.
 */
@Controller
@RequestMapping("/bookorder*")
public class BookOrderController 
{
	@Resource
    private BookOrderService bookorderService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("bookorder/bookorder", "bookorderForm", new  BookOrder());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showBookOrders() {
		ModelAndView mav = new ModelAndView("bookorder/bookorderList");
		List<BookOrder> list = bookorderService.loadAll();
		mav.addObject("listBookOrders", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookorderForm") BookOrder bookorder, BindingResult result) {
		if (!result.hasErrors()) {
			bookorderService.save(bookorder);
		}
		return "redirect:/bookorder/search";
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute("bookorder/edit") BookOrder bookorder, @PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("bookorder/bookorder");

		BookOrder bookorderloaded = bookorderService.load(id);

		modelAndView.addObject("bookorderForm", bookorderloaded);
		return modelAndView;
	}
}
