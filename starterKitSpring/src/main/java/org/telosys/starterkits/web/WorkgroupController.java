/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:07:55 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/workgroup*")
public class WorkgroupController 
{
	@Resource
    private WorkgroupService workgroupService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("workgroup/workgroup", "workgroupForm", new  Workgroup());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showWorkgroups() {
		ModelAndView mav = new ModelAndView("workgroup/workgroupList");
		List<Workgroup> list = workgroupService.loadAll();
		mav.addObject("listWorkgroups", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("workgroupForm") Workgroup workgroup, BindingResult result) {
		if (!result.hasErrors()) {
			workgroupService.save(workgroup);
		}
		return "redirect:/workgroup/search";
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute("workgroup/edit") Workgroup workgroup, @PathVariable("id") Short id) {
		ModelAndView modelAndView = new ModelAndView("workgroup/workgroup");

		Workgroup workgrouploaded = workgroupService.load(id);

		modelAndView.addObject("workgroupForm", workgrouploaded);
		return modelAndView;
	}
}