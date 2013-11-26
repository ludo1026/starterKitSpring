/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:06:01 )
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

import org.telosys.starterkits.bean.Badge;

import org.telosys.starterkits.service.BadgeService;

/**
 * Badge.
 */
@Controller
@RequestMapping("/badge*")
public class BadgeController 
{
	@Resource
    private BadgeService badgeService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("badge/badge", "badgeForm", new  Badge());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showBadges() {
		ModelAndView mav = new ModelAndView("badge/badgeList");
		List<Badge> list = badgeService.loadAll();
		mav.addObject("listBadges", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("badgeForm") Badge badge, BindingResult result) {
		if (!result.hasErrors()) {
			badgeService.save(badge);
		}
		return "redirect:/badge/search";
	}

	@RequestMapping(value = "/edit/{badgeNumber}")
	public ModelAndView edit(@ModelAttribute("badge/edit") Badge badge, @PathVariable("badgeNumber") Integer badgeNumber) {
		ModelAndView modelAndView = new ModelAndView("badge/badge");

		Badge badgeloaded = badgeService.load(badgeNumber);

		modelAndView.addObject("badgeForm", badgeloaded);
		return modelAndView;
	}
}
