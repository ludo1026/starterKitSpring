/*
 * Controller class 
 * Created on 27 nov. 2013 ( Time 18:10:08 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/synopsis")
public class SynopsisController 
{
	@Resource
    private SynopsisService synopsisService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Synopsis synopsis) {
		uiModel.addAttribute("synopsis", synopsis);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Synopsis());
		return "synopsis/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Synopsis> list = synopsisService.loadAll();
		uiModel.addAttribute("listSynopsiss", list);
		return "synopsis/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("synopsisForm") Synopsis synopsis, BindingResult result) {
		if (!result.hasErrors()) {
			synopsisService.save(synopsis);
		}
		return "redirect:/synopsis";
	}

	@RequestMapping(value = "/{bookId}")
	public String edit(Model uiModel, @PathVariable("bookId") Integer bookId) {
		Synopsis synopsis = synopsisService.load(bookId);
		this.populateEditForm(uiModel, synopsis);
		return "synopsis/edit";
	}

	@RequestMapping(value = "/delete/{bookId}")
	public String delete(Model uiModel, @PathVariable("bookId") Integer bookId) {
		synopsisService.delete(bookId);
		return "redirect:/synopsis";
	}
	
}
