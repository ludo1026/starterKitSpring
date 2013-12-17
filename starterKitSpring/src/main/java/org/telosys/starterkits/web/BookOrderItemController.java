   package org.telosys.starterkits.web;

import java.util.List;
import java.math.BigDecimal;

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
import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;
import org.telosys.starterkits.service.BookOrderItemService;
import org.telosys.starterkits.service.BookService;
import org.telosys.starterkits.service.BookOrderService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * BookOrderItem.
 */
@Controller
@RequestMapping("/bookorderitem")
public class BookOrderItemController 
{
	@Resource
    private BookOrderItemService bookorderitemService;
	@Resource
	private ControllerHelper controllerHelper;
	@Resource
    private BookService bookService;
	@Resource
    private BookOrderService bookorderService;

	void populateEditForm(Model uiModel, BookOrderItem bookorderitem) {
		uiModel.addAttribute("bookorderitem", bookorderitem);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("books", bookService.loadAll());
    	uiModel.addAttribute("bookorders", bookorderService.loadAll());
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
	public String save(@Valid BookOrderItem bookorderitem, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			bookorderitem = bookorderitemService.save(bookorderitem);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/bookorderitem/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, bookorderitem.getBook().getId(), bookorderitem.getBookOrder().getId());
		} else {
			return "bookorderitem/edit";
		}
	}

	@RequestMapping(value = "/{book_id}/{bookOrder_id}")
	public String edit(Model uiModel, @PathVariable("book_id") Integer book_id, @PathVariable("bookOrder_id") Integer bookOrder_id) {
		BookOrderItemId bookorderitemid = new BookOrderItemId();
		bookorderitemid.setBook(bookService.load(book_id));
		bookorderitemid.setBookOrder(bookorderService.load(bookOrder_id));
		BookOrderItem bookorderitem = bookorderitemService.load(bookorderitemid);
		this.populateEditForm(uiModel, bookorderitem);
		return "bookorderitem/edit";
	}

	@RequestMapping(value = "/delete/{book_id}/{bookOrder_id}")
	public String delete(Model uiModel, @PathVariable("book_id") Integer book_id, @PathVariable("bookOrder_id") Integer bookOrder_id) {
		BookOrderItemId bookorderitemid = new BookOrderItemId();
		bookorderitemid.setBook(bookService.load(book_id));
		bookorderitemid.setBookOrder(bookorderService.load(bookOrder_id));
		bookorderitemService.delete(bookorderitemid);
		return "redirect:/bookorderitem";
	}
	
}
