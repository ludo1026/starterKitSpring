package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.service.PublisherService;
import org.telosys.starterkits.service.CountryService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
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
	@Resource
    private CountryService countryService;

	void populateEditForm(Model uiModel, Publisher publisher) {
		uiModel.addAttribute("publisher", publisher);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("countrys", countryService.loadAll());
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
	public String save(@Valid Publisher publisher, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			publisher = publisherService.save(publisher);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/publisher/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, publisher.getCode());
		} else {
			return "publisher/edit";
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
