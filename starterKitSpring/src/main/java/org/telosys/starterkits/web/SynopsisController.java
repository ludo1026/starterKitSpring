/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:56 )
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

import org.telosys.starterkits.bean.Synopsis;

import org.telosys.starterkits.service.SynopsisService;

/**
 * Synopsis.
 */
@Controller
@RequestMapping("/synopsis*")
public class SynopsisController 
{
    private SynopsisService synopsisService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("synopsis/synopsis", "command", new  Synopsis());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showSynopsiss() {
		synopsisService = new SynopsisService();
		ModelAndView mav = new ModelAndView("synopsis/synopsisList");
		List<Synopsis> list = synopsisService.loadAll();
		mav.addObject("listSynopsiss", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("synopsisForm") Synopsis synopsis, BindingResult result) {
		synopsisService = new SynopsisService();
		if (!result.hasErrors()) {
			synopsisService.save(synopsis);
		}
		return "redirect:/synopsis/search";
	}

	@RequestMapping(value = "/edit/{bookId}")
	public ModelAndView edit(@ModelAttribute("synopsis/edit") Synopsis synopsis, @PathVariable("bookId") Integer bookId) {
		synopsisService = new SynopsisService();
		ModelAndView modelAndView = new ModelAndView("synopsis/synopsis");

		Synopsis synopsisloaded = synopsisService.load(bookId);

		modelAndView.addObject("current", synopsisloaded);
		return modelAndView;
	}
}
