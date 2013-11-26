/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:06:41 )
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

import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;
import org.telosys.starterkits.service.BookOrderItemService;

/**
 * BookOrderItem.
 */
@Controller
@RequestMapping("/bookorderitemForm")
public class BookOrderItemFormController 
{
	@Resource
    private BookOrderItemService bookorderitemService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookorderitemForm") BookOrderItem bookorderitem, BindingResult result) {
		if (!result.hasErrors()) {
			bookorderitemService.save(bookorderitem);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/bookorderitem/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("bookorderitem/delete") BookOrderItem bookorderitem, @PathVariable("id") BookOrderItemId id) {
		if (id != null){
			bookorderitemService.delete(id);
		}
		return "redirect:/bookorderitem/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("bookorderitemForm") BookOrderItem bookorderitem, BindingResult result) {
		ModelAndView mav = new ModelAndView("bookorderitem/bookorderitemList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<BookOrderItem> list = bookorderitemService.search(criteria);
		mav.addObject("listbookorderitems", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("bookorderitem/bookorderitem", "bookorderitemForm", new BookOrderItem());
	}
}
