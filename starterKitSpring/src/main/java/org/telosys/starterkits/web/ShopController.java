/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:53 )
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

import org.telosys.starterkits.bean.Shop;

import org.telosys.starterkits.service.ShopService;

/**
 * Shop.
 */
@Controller
@RequestMapping("/shop*")
public class ShopController 
{
    private ShopService shopService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("shop/shop", "command", new  Shop());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showShops() {
		shopService = new ShopService();
		ModelAndView mav = new ModelAndView("shop/shopList");
		List<Shop> list = shopService.loadAll();
		mav.addObject("listShops", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("shopForm") Shop shop, BindingResult result) {
		shopService = new ShopService();
		if (!result.hasErrors()) {
			shopService.save(shop);
		}
		return "redirect:/shop/search";
	}

	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@ModelAttribute("shop/edit") Shop shop, @PathVariable("code") String code) {
		shopService = new ShopService();
		ModelAndView modelAndView = new ModelAndView("shop/shop");

		Shop shoploaded = shopService.load(code);

		modelAndView.addObject("current", shoploaded);
		return modelAndView;
	}
}
