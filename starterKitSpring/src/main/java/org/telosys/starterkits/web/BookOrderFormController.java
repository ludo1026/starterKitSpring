/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:37 )
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

import org.telosys.starterkits.bean.BookOrder;
import org.telosys.starterkits.service.BookOrderService;

/**
 * BookOrder.
 */
@Controller
@RequestMapping("/bookorderform")
public class BookOrderFormController 
{
    private BookOrderService bookorderService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookorderform") BookOrder bookorder, BindingResult result) {
		bookorderService = new BookOrderService();
		if (!result.hasErrors()) {
			bookorderService.save(bookorder);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/bookorder/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("bookorder/delete") BookOrder bookorder, @PathVariable("id") Integer id) {
		bookorderService = new BookOrderService();
		if (id != null){
			bookorderService.delete(id);
		}
		return "redirect:/bookorder/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("bookorderform") BookOrder bookorder, BindingResult result) {
		bookorderService = new BookOrderService();
		ModelAndView mav = new ModelAndView("bookorder/bookorderList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<BookOrder> list = bookorderService.search(criteria);
		mav.addObject("listbookorders", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("bookorder/bookorder", "command", new BookOrder());
	}
}
