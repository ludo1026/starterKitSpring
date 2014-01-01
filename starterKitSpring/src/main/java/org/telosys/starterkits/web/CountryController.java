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
import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.service.CountryService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Country.
 */
@Controller
@RequestMapping("/country")
public class CountryController 
{
	@Resource
    private CountryService countryService;
	@Resource
	private ControllerHelper controllerHelper;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Country> list = countryService.loadAll();
		uiModel.addAttribute("listCountrys", list);
		return "country/list";
	}

	void populateForm(Model uiModel, Country country) {
		uiModel.addAttribute("country", country);
		// Listes déroulantes des objets liés
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Country());
		return "country/create";
	}

	@RequestMapping(value = "/{code}")
	public String displayEditForm(Model uiModel, @PathVariable("code") String code) {
		Country country = countryService.load(code);
		this.populateForm(uiModel, country);
		return "country/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Country country, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			if(countryService.load(country.getCode()) != null) {
				result.addError(new ObjectError("country", "already.exists"));
			}
		}
		if (!result.hasErrors()) {
			country = countryService.save(country);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/country/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, country.getCode());
		} else {
			populateForm(uiModel, country);
			return "country/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Country country, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			country = countryService.save(country);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/country/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, country.getCode());
		} else {
			populateForm(uiModel, country);
			return "country/edit";
		}
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(Model uiModel, @PathVariable("code") String code) {
		countryService.delete(code);
		return "redirect:/country";
	}
	
}
