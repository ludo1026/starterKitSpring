package org.telosys.starterkits.web;

import java.util.List;

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
	public String save(@Valid BookOrderItem bookorderitem, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			bookorderitem = bookorderitemService.save(bookorderitem);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/bookorderitem/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, bookorderitem.getId().getBookId(), bookorderitem.getId().getBookOrderId());
		} else {
			return "bookorderitem/edit";
		}
	}

	@RequestMapping(value = "/{bookId}/{bookOrderId}")
	public String edit(Model uiModel, @PathVariable("bookId") Integer bookId, @PathVariable("bookOrderId") Integer bookOrderId) {
		BookOrderItemId id = new BookOrderItemId();
		id.setBookId(bookId);
		id.setBookOrderId(bookOrderId);
		BookOrderItem bookorderitem = bookorderitemService.load(id);
		this.populateEditForm(uiModel, bookorderitem);
		return "bookorderitem/edit";
	}

	@RequestMapping(value = "/delete/{bookId}/{bookOrderId}")
	public String delete(Model uiModel, @PathVariable("bookId") Integer bookId, @PathVariable("bookOrderId") Integer bookOrderId) {
		BookOrderItemId id = new BookOrderItemId();
		id.setBookId(bookId);
		id.setBookOrderId(bookOrderId);
		bookorderitemService.delete(id);
		return "redirect:/bookorderitem";
	}
	
}
