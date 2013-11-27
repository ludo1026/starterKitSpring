package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import org.telosys.starterkits.bean.Publisher;

import org.telosys.starterkits.service.PublisherService;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Publisher.
 */
@Controller
@RequestMapping("/publisher")
public class PublisherController 
{
	@Resource
    private PublisherService publisherService;
	@Resource
	private ControllerHelper controllerHelper;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Publisher publisher) {
		uiModel.addAttribute("publisher", publisher);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Publisher());
		return "publisher/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Publisher> list = publisherService.loadAll();
		uiModel.addAttribute("listPublishers", list);
		return "publisher/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("publisherForm") Publisher publisher, BindingResult result, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			publisher = publisherService.save(publisher);
			return "redirect:/publisher/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, publisher.getCode());
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/{code}")
	public String edit(Model uiModel, @PathVariable("code") Integer code) {
		Publisher publisher = publisherService.load(code);
		this.populateEditForm(uiModel, publisher);
		return "publisher/edit";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(Model uiModel, @PathVariable("code") Integer code) {
		publisherService.delete(code);
		return "redirect:/publisher";
	}
	
}
