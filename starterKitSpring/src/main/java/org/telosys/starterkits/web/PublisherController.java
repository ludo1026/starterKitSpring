package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.service.PublisherService;
import org.telosys.starterkits.service.CountryService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;
import org.telosys.starterkits.web.helper.MessageHelper;

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
    private CountryService countryService;
	@Resource
	private ControllerHelper controllerHelper;
	@Resource
	private MessageHelper messageHelper;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Publisher> list = publisherService.loadAll();
		uiModel.addAttribute("listPublishers", list);
		return "publisher/list";
	}

	void populateForm(Model uiModel, Publisher publisher) {
		uiModel.addAttribute("publisher", publisher);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("countrys", countryService.loadAll());
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Publisher());
		return "publisher/create";
	}

	@RequestMapping(value = "/{code}")
	public String displayEditForm(Model uiModel, @PathVariable("code") Integer code) {
		Publisher publisher = publisherService.load(code);
		this.populateForm(uiModel, publisher);
		return "publisher/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Publisher publisher, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				publisher = publisherService.save(publisher);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/publisher/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, publisher.getCode());
			} else {
				populateForm(uiModel, publisher);
				return "publisher/create";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "publisher.error.create", e);
			populateForm(uiModel, publisher);
			return "publisher/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Publisher publisher, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				publisher = publisherService.save(publisher);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/publisher/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, publisher.getCode());
			} else {
				populateForm(uiModel, publisher);
				return "publisher/edit";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "publisher.error.update", e);
			populateForm(uiModel, publisher);
			return "publisher/edit";
		}
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("code") Integer code) {
		try {
			publisherService.delete(code);
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "publisher.error.delete", e);
		}
		return "redirect:/publisher";
	}
	
}
