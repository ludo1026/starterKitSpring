/*
 * Controller class 
 * Created on 27 nov. 2013 ( Time 18:10:08 )
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

import org.telosys.starterkits.bean.Workgroup;

import org.telosys.starterkits.service.WorkgroupService;

/**
 * Workgroup.
 */
@Controller
@RequestMapping("/workgroup")
public class WorkgroupController 
{
	@Resource
    private WorkgroupService workgroupService;

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
	public String save(@ModelAttribute("workgroupForm") Workgroup workgroup, BindingResult result) {
		if (!result.hasErrors()) {
			workgroupService.save(workgroup);
		}
		return "redirect:/workgroup";
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
