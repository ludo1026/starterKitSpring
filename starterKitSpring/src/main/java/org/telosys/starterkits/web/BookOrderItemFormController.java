/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:39 )
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

import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;
import org.telosys.starterkits.service.BookOrderItemService;

/**
 * BookOrderItem.
 */
@Controller
@RequestMapping("/bookorderitemform")
public class BookOrderItemFormController 
{
    private BookOrderItemService bookorderitemService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookorderitemform") BookOrderItem bookorderitem, BindingResult result) {
		bookorderitemService = new BookOrderItemService();
		if (!result.hasErrors()) {
			bookorderitemService.save(bookorderitem);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/bookorderitem/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("bookorderitem/delete") BookOrderItem bookorderitem, @PathVariable("id") BookOrderItemId id) {
		bookorderitemService = new BookOrderItemService();
		if (id != null){
			bookorderitemService.delete(id);
		}
		return "redirect:/bookorderitem/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("bookorderitemform") BookOrderItem bookorderitem, BindingResult result) {
		bookorderitemService = new BookOrderItemService();
		ModelAndView mav = new ModelAndView("bookorderitem/bookorderitemList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<BookOrderItem> list = bookorderitemService.search(criteria);
		mav.addObject("listbookorderitems", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("bookorderitem/bookorderitem", "command", new BookOrderItem());
	}
}
