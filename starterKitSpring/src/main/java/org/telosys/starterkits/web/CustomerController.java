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
import org.telosys.starterkits.bean.Customer;
import org.telosys.starterkits.service.CustomerService;
import org.telosys.starterkits.service.CountryService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Customer.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController 
{
	@Resource
    private CustomerService customerService;
	@Resource
	private ControllerHelper controllerHelper;
	@Resource
    private CountryService countryService;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Customer> list = customerService.loadAll();
		uiModel.addAttribute("listCustomers", list);
		return "customer/list";
	}

	void populateForm(Model uiModel, Customer customer) {
		uiModel.addAttribute("customer", customer);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("countrys", countryService.loadAll());
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Customer());
		return "customer/create";
	}

	@RequestMapping(value = "/{code}")
	public String displayEditForm(Model uiModel, @PathVariable("code") String code) {
		Customer customer = customerService.load(code);
		this.populateForm(uiModel, customer);
		return "customer/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Customer customer, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			customer = customerService.save(customer);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/customer/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, customer.getCode());
		} else {
			populateForm(uiModel, customer);
			return "customer/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Customer customer, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			customer = customerService.save(customer);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/customer/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, customer.getCode());
		} else {
			populateForm(uiModel, customer);
			return "customer/edit";
		}
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(Model uiModel, @PathVariable("code") String code) {
		customerService.delete(code);
		return "redirect:/customer";
	}
	
}
