/*
 * Controller class 
 * Created on 20 nov. 2013 ( Time 11:56:30 )
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

import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.service.CountryService;


@Controller
@RequestMapping("/country*")
public class CountryController 
{
    private CountryService countryService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("country/country", "command", new  Country());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showCountrys() {
		countryService = new CountryService();
		ModelAndView mav = new ModelAndView("country/countryList");
		List<Country> list = countryService.loadAll();
		mav.addObject("listCountrys", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("countryForm") Country country, BindingResult result) {
		countryService = new CountryService();
		if (!result.hasErrors()) {
			countryService.save(country);
		}
		return "redirect:/country/search";
	}

	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@ModelAttribute("country/edit") Country country, @PathVariable("code") String code) {
		countryService = new CountryService();
		ModelAndView modelAndView = new ModelAndView("country/country");
		if (id != null){
			Country countryloaded = countryService.load(code);
			modelAndView.addObject("current", countryloaded);
		}
		return modelAndView;
	}
}
