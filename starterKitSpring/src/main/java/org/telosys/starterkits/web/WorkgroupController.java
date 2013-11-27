package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import org.telosys.starterkits.bean.Workgroup;

import org.telosys.starterkits.service.WorkgroupService;
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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Workgroup workgroup) {
		uiModel.addAttribute("workgroup", workgroup);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Workgroup());
		return "workgroup/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Workgroup> list = workgroupService.loadAll();
		uiModel.addAttribute("listWorkgroups", list);
		return "workgroup/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("workgroupForm") Workgroup workgroup, BindingResult result, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			workgroup = workgroupService.save(workgroup);
			return "redirect:/workgroup/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, workgroup.getId());
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/{id}")
	public String edit(Model uiModel, @PathVariable("id") Short id) {
		Workgroup workgroup = workgroupService.load(id);
		this.populateEditForm(uiModel, workgroup);
		return "workgroup/edit";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model uiModel, @PathVariable("id") Short id) {
		workgroupService.delete(id);
		return "redirect:/workgroup";
	}
	
}
