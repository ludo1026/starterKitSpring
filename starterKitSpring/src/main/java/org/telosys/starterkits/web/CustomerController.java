package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import org.telosys.starterkits.bean.Customer;

import org.telosys.starterkits.service.CustomerService;
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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Customer customer) {
		uiModel.addAttribute("customer", customer);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Customer());
		return "customer/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Customer> list = customerService.loadAll();
		uiModel.addAttribute("listCustomers", list);
		return "customer/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("customerForm") Customer customer, BindingResult result, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			customer = customerService.save(customer);
			return "redirect:/customer/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, customer.getCode());
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/{code}")
	public String edit(Model uiModel, @PathVariable("code") String code) {
		Customer customer = customerService.load(code);
		this.populateEditForm(uiModel, customer);
		return "customer/edit";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(Model uiModel, @PathVariable("code") String code) {
		customerService.delete(code);
		return "redirect:/customer";
	}
	
}
