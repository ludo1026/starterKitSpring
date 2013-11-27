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

import org.telosys.starterkits.bean.BookOrderItem;

import org.telosys.starterkits.bean.BookOrderItemId;
  import org.telosys.starterkits.service.BookOrderItemService;

/**
 * BookOrderItem.
 */
@Controller
@RequestMapping("/bookorderitem")
public class BookOrderItemController 
{
	@Resource
    private BookOrderItemService bookorderitemService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, BookOrderItem bookorderitem) {
		uiModel.addAttribute("bookorderitem", bookorderitem);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new BookOrderItem());
		return "bookorderitem/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<BookOrderItem> list = bookorderitemService.loadAll();
		uiModel.addAttribute("listBookOrderItems", list);
		return "bookorderitem/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("bookorderitemForm") BookOrderItem bookorderitem, BindingResult result) {
		if (!result.hasErrors()) {
			bookorderitemService.save(bookorderitem);
		}
		return "redirect:/bookorderitem";
	}

	@RequestMapping(value = "/{bookOrderId}/{bookId}")
	public String edit(Model uiModel, @PathVariable("bookOrderId") Integer bookOrderId, @PathVariable("bookId") Integer bookId) {
		BookOrderItemId id = new BookOrderItemId();
		id.setBookOrderId(bookOrderId);
		id.setBookId(bookId);
		BookOrderItem bookorderitem = bookorderitemService.load(id);
		this.populateEditForm(uiModel, bookorderitem);
		return "bookorderitem/edit";
	}

	@RequestMapping(value = "/delete/{bookOrderId}/{bookId}")
	public String delete(Model uiModel, @PathVariable("bookOrderId") Integer bookOrderId, @PathVariable("bookId") Integer bookId) {
		BookOrderItemId id = new BookOrderItemId();
		id.setBookOrderId(bookOrderId);
		id.setBookId(bookId);
		bookorderitemService.delete(id);
		return "redirect:/bookorderitem";
	}
	
}
