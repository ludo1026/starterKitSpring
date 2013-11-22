/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:33 )
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

import org.telosys.starterkits.bean.Badge;

import org.telosys.starterkits.service.BadgeService;

/**
 * Badge.
 */
@Controller
@RequestMapping("/badge*")
public class BadgeController 
{
    private BadgeService badgeService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("badge/badge", "command", new  Badge());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showBadges() {
		badgeService = new BadgeService();
		ModelAndView mav = new ModelAndView("badge/badgeList");
		List<Badge> list = badgeService.loadAll();
		mav.addObject("listBadges", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("badgeForm") Badge badge, BindingResult result) {
		badgeService = new BadgeService();
		if (!result.hasErrors()) {
			badgeService.save(badge);
		}
		return "redirect:/badge/search";
	}

	@RequestMapping(value = "/edit/{badgeNumber}")
	public ModelAndView edit(@ModelAttribute("badge/edit") Badge badge, @PathVariable("badgeNumber") Integer badgeNumber) {
		badgeService = new BadgeService();
		ModelAndView modelAndView = new ModelAndView("badge/badge");

		Badge badgeloaded = badgeService.load(badgeNumber);

		modelAndView.addObject("current", badgeloaded);
		return modelAndView;
	}
}
