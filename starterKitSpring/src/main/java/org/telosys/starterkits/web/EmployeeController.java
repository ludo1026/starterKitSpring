/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:07:02 )
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

import org.telosys.starterkits.bean.Employee;

import org.telosys.starterkits.service.EmployeeService;

/**
 * Employee.
 */
@Controller
@RequestMapping("/employee*")
public class EmployeeController 
{
	@Resource
    private EmployeeService employeeService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("employee/employee", "employeeForm", new  Employee());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showEmployees() {
		ModelAndView mav = new ModelAndView("employee/employeeList");
		List<Employee> list = employeeService.loadAll();
		mav.addObject("listEmployees", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("employeeForm") Employee employee, BindingResult result) {
		if (!result.hasErrors()) {
			employeeService.save(employee);
		}
		return "redirect:/employee/search";
	}

	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@ModelAttribute("employee/edit") Employee employee, @PathVariable("code") String code) {
		ModelAndView modelAndView = new ModelAndView("employee/employee");

		Employee employeeloaded = employeeService.load(code);

		modelAndView.addObject("employeeForm", employeeloaded);
		return modelAndView;
	}
}