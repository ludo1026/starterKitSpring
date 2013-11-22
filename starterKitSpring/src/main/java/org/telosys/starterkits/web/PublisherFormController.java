/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:50 )
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

import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.service.PublisherService;

/**
 * Publisher.
 */
@Controller
@RequestMapping("/publisherform")
public class PublisherFormController 
{
    private PublisherService publisherService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("publisherform") Publisher publisher, BindingResult result) {
		publisherService = new PublisherService();
		if (!result.hasErrors()) {
			publisherService.save(publisher);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/publisher/list";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(@ModelAttribute("publisher/delete") Publisher publisher, @PathVariable("code") Integer code) {
		publisherService = new PublisherService();
		if (code != null){
			publisherService.delete(code);
		}
		return "redirect:/publisher/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("publisherform") Publisher publisher, BindingResult result) {
		publisherService = new PublisherService();
		ModelAndView mav = new ModelAndView("publisher/publisherList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Publisher> list = publisherService.search(criteria);
		mav.addObject("listpublishers", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("publisher/publisher", "command", new Publisher());
	}
}
