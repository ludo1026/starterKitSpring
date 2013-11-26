/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:07:26 )
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

import org.telosys.starterkits.bean.Publisher;

import org.telosys.starterkits.service.PublisherService;

/**
 * Publisher.
 */
@Controller
@RequestMapping("/publisher*")
public class PublisherController 
{
	@Resource
    private PublisherService publisherService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("publisher/publisher", "publisherForm", new  Publisher());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showPublishers() {
		ModelAndView mav = new ModelAndView("publisher/publisherList");
		List<Publisher> list = publisherService.loadAll();
		mav.addObject("listPublishers", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("publisherForm") Publisher publisher, BindingResult result) {
		if (!result.hasErrors()) {
			publisherService.save(publisher);
		}
		return "redirect:/publisher/search";
	}

	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@ModelAttribute("publisher/edit") Publisher publisher, @PathVariable("code") Integer code) {
		ModelAndView modelAndView = new ModelAndView("publisher/publisher");

		Publisher publisherloaded = publisherService.load(code);

		modelAndView.addObject("publisherForm", publisherloaded);
		return modelAndView;
	}
}
