/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:06:54 )
 */

package org.telosys.starterkits.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/customerForm")
public class CustomerFormController 
{
	@Resource
    private CustomerService customerService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("customerForm") Customer customer, BindingResult result) {
		if (!result.hasErrors()) {
			customerService.save(customer);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(@ModelAttribute("customer/delete") Customer customer, @PathVariable("code") String code) {
		if (code != null){
			customerService.delete(code);
		}
		return "redirect:/customer/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("customerForm") Customer customer, BindingResult result) {
		ModelAndView mav = new ModelAndView("customer/customerList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Customer> list = customerService.search(criteria);
		mav.addObject("listcustomers", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("customer/customer", "customerForm", new Customer());
	}
}
