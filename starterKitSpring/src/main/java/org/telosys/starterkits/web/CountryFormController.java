/*
 * Controller class 
 * Created on 21 nov. 2013 ( Time 14:59:59 )
 */

package org.telosys.starterkits.web;

import java.util.ArrayList;
import java.util.List;

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
import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.service.CountryService;


@Controller
@RequestMapping("/countryform")
public class CountryFormController 
{
    private CountryService countryService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("countryform") Country country, BindingResult result) {
		countryService = new CountryService();
		if (!result.hasErrors()) {
			countryService.save(country);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/country/list";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(@ModelAttribute("country/delete") Country country, @PathVariable("code") String code) {
		countryService = new CountryService();
		if (code != null){
			countryService.delete(code);
		}
		return "redirect:/country/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("countryform") Country country, BindingResult result) {
		countryService = new CountryService();
		ModelAndView mav = new ModelAndView("country/countryList");
		List<Country> list = new ArrayList<Country>();
		//.search(country);
		mav.addObject("listcountrys", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("country/country", "command", new Country());
	}
}
