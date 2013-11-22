/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:33 )
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

import org.telosys.starterkits.bean.Badge;
import org.telosys.starterkits.service.BadgeService;

/**
 * Badge.
 */
@Controller
@RequestMapping("/badgeform")
public class BadgeFormController 
{
    private BadgeService badgeService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("badgeform") Badge badge, BindingResult result) {
		badgeService = new BadgeService();
		if (!result.hasErrors()) {
			badgeService.save(badge);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/badge/list";
	}

	@RequestMapping(value = "/delete/{badgeNumber}")
	public String delete(@ModelAttribute("badge/delete") Badge badge, @PathVariable("badgeNumber") Integer badgeNumber) {
		badgeService = new BadgeService();
		if (badgeNumber != null){
			badgeService.delete(badgeNumber);
		}
		return "redirect:/badge/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("badgeform") Badge badge, BindingResult result) {
		badgeService = new BadgeService();
		ModelAndView mav = new ModelAndView("badge/badgeList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Badge> list = badgeService.search(criteria);
		mav.addObject("listbadges", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("badge/badge", "command", new Badge());
	}
}
