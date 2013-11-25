/*
 * Controller class 
 * Created on 25 nov. 2013 ( Time 17:11:51 )
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

import org.telosys.starterkits.bean.Country;

import org.telosys.starterkits.service.CountryService;

/**
 * Country.
 */
@Controller
@RequestMapping("/country*")
public class CountryController 
{
	@Resource
    private CountryService countryService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("country/country", "countryForm", new  Country());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showCountrys() {
		ModelAndView mav = new ModelAndView("country/countryList");
		List<Country> list = countryService.loadAll();
		mav.addObject("listCountrys", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("countryForm") Country country, BindingResult result) {
		if (!result.hasErrors()) {
			countryService.save(country);
		}
		return "redirect:/country/search";
	}

	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@ModelAttribute("country/edit") Country country, @PathVariable("code") String code) {
		ModelAndView modelAndView = new ModelAndView("country/country");

		Country countryloaded = countryService.load(code);

		modelAndView.addObject("countryForm", countryloaded);
		return modelAndView;
	}
}
