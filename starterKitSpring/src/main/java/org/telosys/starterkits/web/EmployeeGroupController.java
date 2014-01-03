package org.telosys.starterkits.web;
   
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;
import org.telosys.starterkits.service.EmployeeGroupService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;

/**
 * EmployeeGroup.
 */
@Controller
@RequestMapping("/employeegroup")
public class EmployeeGroupController extends AbstractController 
{
	@Resource
    private EmployeeGroupService employeegroupService;

	@RequestMapping()
	public String list(Model uiModel) {
		List<EmployeeGroup> list = employeegroupService.loadAll();
		uiModel.addAttribute("listEmployeeGroups", list);
		return "employeegroup/list";
	}

	void populateForm(Model uiModel, EmployeeGroup employeegroup) {
		uiModel.addAttribute("employeegroup", employeegroup);
		// Listes déroulantes des objets liés
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new EmployeeGroup());
		return "employeegroup/create";
	}

	@RequestMapping(value = "/{employeeCode}/{groupId}")
	public String displayEditForm(Model uiModel, @PathVariable("employeeCode") String employeeCode, @PathVariable("groupId") Short groupId) {
		EmployeeGroupId employeegroupid = new EmployeeGroupId();
		employeegroupid.setEmployeeCode(employeeCode);
		employeegroupid.setGroupId(groupId);
		EmployeeGroup employeegroup = employeegroupService.load(employeegroupid);
		this.populateForm(uiModel, employeegroup);
		return "employeegroup/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid EmployeeGroup employeegroup, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				employeegroup = employeegroupService.save(employeegroup);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/employeegroup/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, employeegroup.getEmployeeCode(), employeegroup.getGroupId());
			} else {
				populateForm(uiModel, employeegroup);
				return "employeegroup/create";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "employeegroup.error.create", e);
			populateForm(uiModel, employeegroup);
			return "employeegroup/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid EmployeeGroup employeegroup, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				employeegroup = employeegroupService.save(employeegroup);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/employeegroup/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, employeegroup.getEmployeeCode(), employeegroup.getGroupId());
			} else {
				populateForm(uiModel, employeegroup);
				return "employeegroup/edit";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "employeegroup.error.update", e);
			populateForm(uiModel, employeegroup);
			return "employeegroup/edit";
		}
	}

	@RequestMapping(value = "/delete/{employeeCode}/{groupId}")
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("employeeCode") String employeeCode, @PathVariable("groupId") Short groupId) {
		try {
			EmployeeGroupId employeegroupid = new EmployeeGroupId();
			employeegroupid.setEmployeeCode(employeeCode);
			employeegroupid.setGroupId(groupId);
			employeegroupService.delete(employeegroupid);
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "employeegroup.error.delete", e);
		}
		return "redirect:/employeegroup";
	}
	
}
