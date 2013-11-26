/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:06:27 )
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

import org.telosys.starterkits.bean.BookOrder;
import org.telosys.starterkits.service.BookOrderService;

/**
 * BookOrder.
 */
@Controller
@RequestMapping("/bookorderForm")
public class BookOrderFormController 
{
	@Resource
    private BookOrderService bookorderService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookorderForm") BookOrder bookorder, BindingResult result) {
		if (!result.hasErrors()) {
			bookorderService.save(bookorder);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/bookorder/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("bookorder/delete") BookOrder bookorder, @PathVariable("id") Integer id) {
		if (id != null){
			bookorderService.delete(id);
		}
		return "redirect:/bookorder/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("bookorderForm") BookOrder bookorder, BindingResult result) {
		ModelAndView mav = new ModelAndView("bookorder/bookorderList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<BookOrder> list = bookorderService.search(criteria);
		mav.addObject("listbookorders", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("bookorder/bookorder", "bookorderForm", new BookOrder());
	}
}
