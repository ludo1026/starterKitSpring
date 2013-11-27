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

import org.telosys.starterkits.bean.Employee;

import org.telosys.starterkits.service.EmployeeService;

/**
 * Employee.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController 
{
	@Resource
    private EmployeeService employeeService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Employee employee) {
		uiModel.addAttribute("employee", employee);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Employee());
		return "employee/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Employee> list = employeeService.loadAll();
		uiModel.addAttribute("listEmployees", list);
		return "employee/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("employeeForm") Employee employee, BindingResult result) {
		if (!result.hasErrors()) {
			employeeService.save(employee);
		}
		return "redirect:/employee";
	}

	@RequestMapping(value = "/{code}")
	public String edit(Model uiModel, @PathVariable("code") String code) {
		Employee employee = employeeService.load(code);
		this.populateEditForm(uiModel, employee);
		return "employee/edit";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(Model uiModel, @PathVariable("code") String code) {
		employeeService.delete(code);
		return "redirect:/employee";
	}
	
}
