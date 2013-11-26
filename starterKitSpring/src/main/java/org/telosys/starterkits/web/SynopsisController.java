/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:07:48 )
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

import org.telosys.starterkits.bean.Synopsis;

import org.telosys.starterkits.service.SynopsisService;

/**
 * Synopsis.
 */
@Controller
@RequestMapping("/synopsis*")
public class SynopsisController 
{
	@Resource
    private SynopsisService synopsisService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("synopsis/synopsis", "synopsisForm", new  Synopsis());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showSynopsiss() {
		ModelAndView mav = new ModelAndView("synopsis/synopsisList");
		List<Synopsis> list = synopsisService.loadAll();
		mav.addObject("listSynopsiss", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("synopsisForm") Synopsis synopsis, BindingResult result) {
		if (!result.hasErrors()) {
			synopsisService.save(synopsis);
		}
		return "redirect:/synopsis/search";
	}

	@RequestMapping(value = "/edit/{bookId}")
	public ModelAndView edit(@ModelAttribute("synopsis/edit") Synopsis synopsis, @PathVariable("bookId") Integer bookId) {
		ModelAndView modelAndView = new ModelAndView("synopsis/synopsis");

		Synopsis synopsisloaded = synopsisService.load(bookId);

		modelAndView.addObject("synopsisForm", synopsisloaded);
		return modelAndView;
	}
}
