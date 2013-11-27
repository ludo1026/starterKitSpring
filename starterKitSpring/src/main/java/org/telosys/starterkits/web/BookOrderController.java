/*
 * Controller class 
 * Created on 27 nov. 2013 ( Time 18:10:06 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/bookorder")
public class BookOrderController 
{
	@Resource
    private BookOrderService bookorderService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, BookOrder bookorder) {
		uiModel.addAttribute("bookorder", bookorder);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new BookOrder());
		return "bookorder/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<BookOrder> list = bookorderService.loadAll();
		uiModel.addAttribute("listBookOrders", list);
		return "bookorder/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("bookorderForm") BookOrder bookorder, BindingResult result) {
		if (!result.hasErrors()) {
			bookorderService.save(bookorder);
		}
		return "redirect:/bookorder";
	}

	@RequestMapping(value = "/{id}")
	public String edit(Model uiModel, @PathVariable("id") Integer id) {
		BookOrder bookorder = bookorderService.load(id);
		this.populateEditForm(uiModel, bookorder);
		return "bookorder/edit";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model uiModel, @PathVariable("id") Integer id) {
		bookorderService.delete(id);
		return "redirect:/bookorder";
	}
	
}
