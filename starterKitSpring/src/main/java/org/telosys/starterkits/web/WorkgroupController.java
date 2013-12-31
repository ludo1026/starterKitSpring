package org.telosys.starterkits.web;

import java.util.List;
import java.util.Date;

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
import org.telosys.starterkits.bean.Workgroup;
import org.telosys.starterkits.service.WorkgroupService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Workgroup.
 */
@Controller
@RequestMapping("/workgroup")
public class WorkgroupController 
{
	@Resource
    private WorkgroupService workgroupService;
	@Resource
	private ControllerHelper controllerHelper;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Workgroup> list = workgroupService.loadAll();
		uiModel.addAttribute("listWorkgroups", list);
		return "workgroup/list";
	}

	void populateForm(Model uiModel, Workgroup workgroup) {
		uiModel.addAttribute("workgroup", workgroup);
		// Listes déroulantes des objets liés
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Workgroup());
		return "workgroup/create";
	}

	@RequestMapping(value = "/{id}")
	public String displayEditForm(Model uiModel, @PathVariable("id") Short id) {
		Workgroup workgroup = workgroupService.load(id);
		this.populateForm(uiModel, workgroup);
		return "workgroup/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Workgroup workgroup, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			workgroup = workgroupService.save(workgroup);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/workgroup/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, workgroup.getId());
		} else {
			populateForm(uiModel, workgroup);
			return "workgroup/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Workgroup workgroup, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			workgroup = workgroupService.save(workgroup);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/workgroup/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, workgroup.getId());
		} else {
			populateForm(uiModel, workgroup);
			return "workgroup/edit";
		}
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model uiModel, @PathVariable("id") Short id) {
		workgroupService.delete(id);
		return "redirect:/workgroup";
	}
	
}
