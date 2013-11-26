/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:06:53 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;

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
	@Resource
    private CustomerService customerService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("customer/customer", "customerForm", new  Customer());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showCustomers() {
		ModelAndView mav = new ModelAndView("customer/customerList");
		List<Customer> list = customerService.loadAll();
		mav.addObject("listCustomers", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("customerForm") Customer customer, BindingResult result) {
		if (!result.hasErrors()) {
			customerService.save(customer);
		}
		return "redirect:/customer/search";
	}

	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@ModelAttribute("customer/edit") Customer customer, @PathVariable("code") String code) {
		ModelAndView modelAndView = new ModelAndView("customer/customer");

		Customer customerloaded = customerService.load(code);

		modelAndView.addObject("customerForm", customerloaded);
		return modelAndView;
	}
}
