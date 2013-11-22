/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:45 )
 */

package org.telosys.starterkits.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.telosys.starterkits.bean.Employee;
import org.telosys.starterkits.service.EmployeeService;

/**
 * Employee.
 */
@Controller
@RequestMapping("/employeeform")
public class EmployeeFormController 
{
    private EmployeeService employeeService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("employeeform") Employee employee, BindingResult result) {
		employeeService = new EmployeeService();
		if (!result.hasErrors()) {
			employeeService.save(employee);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/employee/list";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(@ModelAttribute("employee/delete") Employee employee, @PathVariable("code") String code) {
		employeeService = new EmployeeService();
		if (code != null){
			employeeService.delete(code);
		}
		return "redirect:/employee/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("employeeform") Employee employee, BindingResult result) {
		employeeService = new EmployeeService();
		ModelAndView mav = new ModelAndView("employee/employeeList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Employee> list = employeeService.search(criteria);
		mav.addObject("listemployees", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("employee/employee", "command", new Employee());
	}
}
