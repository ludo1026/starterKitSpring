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

import org.telosys.starterkits.bean.EmployeeGroup;

import org.telosys.starterkits.bean.EmployeeGroupId;
  import org.telosys.starterkits.service.EmployeeGroupService;

/**
 * EmployeeGroup.
 */
@Controller
@RequestMapping("/employeegroup")
public class EmployeeGroupController 
{
	@Resource
    private EmployeeGroupService employeegroupService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, EmployeeGroup employeegroup) {
		uiModel.addAttribute("employeegroup", employeegroup);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new EmployeeGroup());
		return "employeegroup/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<EmployeeGroup> list = employeegroupService.loadAll();
		uiModel.addAttribute("listEmployeeGroups", list);
		return "employeegroup/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("employeegroupForm") EmployeeGroup employeegroup, BindingResult result) {
		if (!result.hasErrors()) {
			employeegroupService.save(employeegroup);
		}
		return "redirect:/employeegroup";
	}

	@RequestMapping(value = "/{employeeCode}/{groupId}")
	public String edit(Model uiModel, @PathVariable("employeeCode") String employeeCode, @PathVariable("groupId") Short groupId) {
		EmployeeGroupId id = new EmployeeGroupId();
		id.setEmployeeCode(employeeCode);
		id.setGroupId(groupId);
		EmployeeGroup employeegroup = employeegroupService.load(id);
		this.populateEditForm(uiModel, employeegroup);
		return "employeegroup/edit";
	}

	@RequestMapping(value = "/delete/{employeeCode}/{groupId}")
	public String delete(Model uiModel, @PathVariable("employeeCode") String employeeCode, @PathVariable("groupId") Short groupId) {
		EmployeeGroupId id = new EmployeeGroupId();
		id.setEmployeeCode(employeeCode);
		id.setGroupId(groupId);
		employeegroupService.delete(id);
		return "redirect:/employeegroup";
	}
	
}
