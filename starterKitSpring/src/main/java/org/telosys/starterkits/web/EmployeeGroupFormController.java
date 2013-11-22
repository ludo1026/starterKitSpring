/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:47 )
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

import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;
import org.telosys.starterkits.service.EmployeeGroupService;

/**
 * EmployeeGroup.
 */
@Controller
@RequestMapping("/employeegroupform")
public class EmployeeGroupFormController 
{
    private EmployeeGroupService employeegroupService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("employeegroupform") EmployeeGroup employeegroup, BindingResult result) {
		employeegroupService = new EmployeeGroupService();
		if (!result.hasErrors()) {
			employeegroupService.save(employeegroup);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/employeegroup/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("employeegroup/delete") EmployeeGroup employeegroup, @PathVariable("id") EmployeeGroupId id) {
		employeegroupService = new EmployeeGroupService();
		if (id != null){
			employeegroupService.delete(id);
		}
		return "redirect:/employeegroup/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("employeegroupform") EmployeeGroup employeegroup, BindingResult result) {
		employeegroupService = new EmployeeGroupService();
		ModelAndView mav = new ModelAndView("employeegroup/employeegroupList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<EmployeeGroup> list = employeegroupService.search(criteria);
		mav.addObject("listemployeegroups", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("employeegroup/employeegroup", "command", new EmployeeGroup());
	}
}
