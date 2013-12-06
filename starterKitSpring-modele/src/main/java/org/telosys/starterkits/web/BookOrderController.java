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
import org.telosys.starterkits.bean.BookOrder;
import org.telosys.starterkits.service.BookOrderService;
import org.telosys.starterkits.service.ShopService;
import org.telosys.starterkits.service.CustomerService;
import org.telosys.starterkits.service.EmployeeService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * BookOrder.
 */
@Controller
@RequestMapping("/bookorder")
public class BookOrderController 
{
	@Resource
    private BookOrderService bookorderService;
	@Resource
	private ControllerHelper controllerHelper;
	@Resource
    private ShopService shopService;
	@Resource
    private CustomerService customerService;
	@Resource
    private EmployeeService employeeService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, BookOrder bookorder) {
		uiModel.addAttribute("bookorder", bookorder);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("shops", shopService.loadAll());
    	uiModel.addAttribute("customers", customerService.loadAll());
    	uiModel.addAttribute("employees", employeeService.loadAll());
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
	public String save(@Valid BookOrder bookorder, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			bookorder = bookorderService.save(bookorder);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/bookorder/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, bookorder.getId());
		} else {
			return "bookorder/edit";
		}
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
