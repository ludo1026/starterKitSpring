/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:47 )
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

import org.telosys.starterkits.bean.EmployeeGroup;

import org.telosys.starterkits.bean.EmployeeGroupId;
import org.telosys.starterkits.service.EmployeeGroupService;

/**
 * EmployeeGroup.
 */
@Controller
@RequestMapping("/employeegroup*")
public class EmployeeGroupController 
{
    private EmployeeGroupService employeegroupService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("employeegroup/employeegroup", "command", new  EmployeeGroup());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showEmployeeGroups() {
		employeegroupService = new EmployeeGroupService();
		ModelAndView mav = new ModelAndView("employeegroup/employeegroupList");
		List<EmployeeGroup> list = employeegroupService.loadAll();
		mav.addObject("listEmployeeGroups", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("employeegroupForm") EmployeeGroup employeegroup, BindingResult result) {
		employeegroupService = new EmployeeGroupService();
		if (!result.hasErrors()) {
			employeegroupService.save(employeegroup);
		}
		return "redirect:/employeegroup/search";
	}

	@RequestMapping(value = "/edit/{employeeCode}/{groupId}")
	public ModelAndView edit(@ModelAttribute("employeegroup/edit") EmployeeGroup employeegroup, @PathVariable("employeeCode") String employeeCode, @PathVariable("groupId") Short groupId) {
		employeegroupService = new EmployeeGroupService();
		ModelAndView modelAndView = new ModelAndView("employeegroup/employeegroup");

		EmployeeGroupId id = new EmployeeGroupId();
		id.setEmployeeCode(employeeCode);
		id.setGroupId(groupId);

		EmployeeGroup employeegrouploaded = employeegroupService.load(id);

		modelAndView.addObject("current", employeegrouploaded);
		return modelAndView;
	}
}
