/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:42 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/customer*")
public class CustomerController 
{
    private CustomerService customerService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("customer/customer", "command", new  Customer());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showCustomers() {
		customerService = new CustomerService();
		ModelAndView mav = new ModelAndView("customer/customerList");
		List<Customer> list = customerService.loadAll();
		mav.addObject("listCustomers", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("customerForm") Customer customer, BindingResult result) {
		customerService = new CustomerService();
		if (!result.hasErrors()) {
			customerService.save(customer);
		}
		return "redirect:/customer/search";
	}

	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@ModelAttribute("customer/edit") Customer customer, @PathVariable("code") String code) {
		customerService = new CustomerService();
		ModelAndView modelAndView = new ModelAndView("customer/customer");

		Customer customerloaded = customerService.load(code);

		modelAndView.addObject("current", customerloaded);
		return modelAndView;
	}
}
