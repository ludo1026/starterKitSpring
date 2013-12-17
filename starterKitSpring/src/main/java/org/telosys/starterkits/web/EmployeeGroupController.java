   package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;
import org.telosys.starterkits.service.EmployeeGroupService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * EmployeeGroup.
 */
@Controller
@RequestMapping("/employeegroup")
public class EmployeeGroupController 
{
	@Resource
    private EmployeeGroupService employeegroupService;
	@Resource
	private ControllerHelper controllerHelper;

	void populateEditForm(Model uiModel, EmployeeGroup employeegroup) {
		uiModel.addAttribute("employeegroup", employeegroup);
		// Listes déroulantes des objets liés
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
	public String save(@Valid EmployeeGroup employeegroup, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			employeegroup = employeegroupService.save(employeegroup);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/employeegroup/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, employeegroup.getEmployeeCode(), employeegroup.getGroupId());
		} else {
			return "employeegroup/edit";
		}
	}

	@RequestMapping(value = "/{employeeCode}/{groupId}")
	public String edit(Model uiModel, @PathVariable("employeeCode") String employeeCode, @PathVariable("groupId") Short groupId) {
		EmployeeGroupId employeegroupid = new EmployeeGroupId();
		employeegroupid.setEmployeeCode(employeeCode);
		employeegroupid.setGroupId(groupId);
		EmployeeGroup employeegroup = employeegroupService.load(employeegroupid);
		this.populateEditForm(uiModel, employeegroup);
		return "employeegroup/edit";
	}

	@RequestMapping(value = "/delete/{employeeCode}/{groupId}")
	public String delete(Model uiModel, @PathVariable("employeeCode") String employeeCode, @PathVariable("groupId") Short groupId) {
		EmployeeGroupId employeegroupid = new EmployeeGroupId();
		employeegroupid.setEmployeeCode(employeeCode);
		employeegroupid.setGroupId(groupId);
		employeegroupService.delete(employeegroupid);
		return "redirect:/employeegroup";
	}
	
}
