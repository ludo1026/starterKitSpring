/*
 * Controller class 
 * Created on 27 nov. 2013 ( Time 18:10:07 )
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

import org.telosys.starterkits.bean.Customer;

import org.telosys.starterkits.service.CustomerService;

/**
 * Customer.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController 
{
	@Resource
    private CustomerService customerService;

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
	public String save(@ModelAttribute("customerForm") Customer customer, BindingResult result) {
		if (!result.hasErrors()) {
			customerService.save(customer);
		}
		return "redirect:/customer";
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
