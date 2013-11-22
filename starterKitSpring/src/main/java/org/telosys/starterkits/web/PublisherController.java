/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:50 )
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

import org.telosys.starterkits.bean.Publisher;

import org.telosys.starterkits.service.PublisherService;

/**
 * Publisher.
 */
@Controller
@RequestMapping("/publisher*")
public class PublisherController 
{
    private PublisherService publisherService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("publisher/publisher", "command", new  Publisher());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showPublishers() {
		publisherService = new PublisherService();
		ModelAndView mav = new ModelAndView("publisher/publisherList");
		List<Publisher> list = publisherService.loadAll();
		mav.addObject("listPublishers", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("publisherForm") Publisher publisher, BindingResult result) {
		publisherService = new PublisherService();
		if (!result.hasErrors()) {
			publisherService.save(publisher);
		}
		return "redirect:/publisher/search";
	}

	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@ModelAttribute("publisher/edit") Publisher publisher, @PathVariable("code") Integer code) {
		publisherService = new PublisherService();
		ModelAndView modelAndView = new ModelAndView("publisher/publisher");

		Publisher publisherloaded = publisherService.load(code);

		modelAndView.addObject("current", publisherloaded);
		return modelAndView;
	}
}
