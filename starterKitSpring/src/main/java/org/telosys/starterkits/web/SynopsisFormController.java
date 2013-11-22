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

import org.telosys.starterkits.bean.Synopsis;
import org.telosys.starterkits.service.SynopsisService;

/**
 * Synopsis.
 */
@Controller
@RequestMapping("/synopsisform")
public class SynopsisFormController 
{
    private SynopsisService synopsisService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("synopsisform") Synopsis synopsis, BindingResult result) {
		synopsisService = new SynopsisService();
		if (!result.hasErrors()) {
			synopsisService.save(synopsis);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/synopsis/list";
	}

	@RequestMapping(value = "/delete/{bookId}")
	public String delete(@ModelAttribute("synopsis/delete") Synopsis synopsis, @PathVariable("bookId") Integer bookId) {
		synopsisService = new SynopsisService();
		if (bookId != null){
			synopsisService.delete(bookId);
		}
		return "redirect:/synopsis/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("synopsisform") Synopsis synopsis, BindingResult result) {
		synopsisService = new SynopsisService();
		ModelAndView mav = new ModelAndView("synopsis/synopsisList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Synopsis> list = synopsisService.search(criteria);
		mav.addObject("listsynopsiss", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("synopsis/synopsis", "command", new Synopsis());
	}
}
