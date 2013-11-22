/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:56 )
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

import org.telosys.starterkits.bean.Workgroup;
import org.telosys.starterkits.service.WorkgroupService;

/**
 * Workgroup.
 */
@Controller
@RequestMapping("/workgroupform")
public class WorkgroupFormController 
{
    private WorkgroupService workgroupService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("workgroupform") Workgroup workgroup, BindingResult result) {
		workgroupService = new WorkgroupService();
		if (!result.hasErrors()) {
			workgroupService.save(workgroup);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/workgroup/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("workgroup/delete") Workgroup workgroup, @PathVariable("id") Short id) {
		workgroupService = new WorkgroupService();
		if (id != null){
			workgroupService.delete(id);
		}
		return "redirect:/workgroup/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("workgroupform") Workgroup workgroup, BindingResult result) {
		workgroupService = new WorkgroupService();
		ModelAndView mav = new ModelAndView("workgroup/workgroupList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Workgroup> list = workgroupService.search(criteria);
		mav.addObject("listworkgroups", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("workgroup/workgroup", "command", new Workgroup());
	}
}
