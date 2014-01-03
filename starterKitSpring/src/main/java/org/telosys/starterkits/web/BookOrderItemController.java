package org.telosys.starterkits.web;
   
import java.util.List;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;
import org.telosys.starterkits.service.BookOrderItemService;
import org.telosys.starterkits.service.BookService;
import org.telosys.starterkits.service.BookOrderService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;

/**
 * BookOrderItem.
 */
@Controller
@RequestMapping("/bookorderitem")
public class BookOrderItemController extends AbstractController 
{
	@Resource
    private BookOrderItemService bookorderitemService;
	@Resource
    private BookService bookService;
	@Resource
    private BookOrderService bookorderService;

	@RequestMapping()
	public String list(Model uiModel) {
		List<BookOrderItem> list = bookorderitemService.loadAll();
		uiModel.addAttribute("listBookOrderItems", list);
		return "bookorderitem/list";
	}

	void populateForm(Model uiModel, BookOrderItem bookorderitem) {
		uiModel.addAttribute("bookorderitem", bookorderitem);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("books", bookService.loadAll());
    	uiModel.addAttribute("bookorders", bookorderService.loadAll());
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new BookOrderItem());
		return "bookorderitem/create";
	}

	@RequestMapping(value = "/{bookId}/{bookOrderId}")
	public String displayEditForm(Model uiModel, @PathVariable("bookId") Integer bookId, @PathVariable("bookOrderId") Integer bookOrderId) {
		BookOrderItemId bookorderitemid = new BookOrderItemId();
		bookorderitemid.setBookId(bookId);
		bookorderitemid.setBookOrderId(bookOrderId);
		BookOrderItem bookorderitem = bookorderitemService.load(bookorderitemid);
		this.populateForm(uiModel, bookorderitem);
		return "bookorderitem/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid BookOrderItem bookorderitem, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				if(bookorderitemService.load(bookorderitem.getId()) != null) {
					result.addError(new ObjectError("bookorderitem", "already.exists"));
				}
			}
			if (!result.hasErrors()) {
				bookorderitem = bookorderitemService.save(bookorderitem);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/bookorderitem/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, bookorderitem.getBookId(), bookorderitem.getBookOrderId());
			} else {
				populateForm(uiModel, bookorderitem);
				return "bookorderitem/create";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "bookorderitem.error.create", e);
			populateForm(uiModel, bookorderitem);
			return "bookorderitem/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid BookOrderItem bookorderitem, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				bookorderitem = bookorderitemService.save(bookorderitem);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/bookorderitem/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, bookorderitem.getBookId(), bookorderitem.getBookOrderId());
			} else {
				populateForm(uiModel, bookorderitem);
				return "bookorderitem/edit";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "bookorderitem.error.update", e);
			populateForm(uiModel, bookorderitem);
			return "bookorderitem/edit";
		}
	}

	@RequestMapping(value = "/delete/{bookId}/{bookOrderId}")
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("bookId") Integer bookId, @PathVariable("bookOrderId") Integer bookOrderId) {
		try {
			BookOrderItemId bookorderitemid = new BookOrderItemId();
			bookorderitemid.setBookId(bookId);
			bookorderitemid.setBookOrderId(bookOrderId);
			bookorderitemService.delete(bookorderitemid);
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "bookorderitem.error.delete", e);
		}
		return "redirect:/bookorderitem";
	}
	
}
