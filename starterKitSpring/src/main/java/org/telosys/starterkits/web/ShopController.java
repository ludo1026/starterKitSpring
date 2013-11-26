/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:07:42 )
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

import org.telosys.starterkits.bean.Shop;

import org.telosys.starterkits.service.ShopService;

/**
 * Shop.
 */
@Controller
@RequestMapping("/shop*")
public class ShopController 
{
	@Resource
    private ShopService shopService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("shop/shop", "shopForm", new  Shop());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showShops() {
		ModelAndView mav = new ModelAndView("shop/shopList");
		List<Shop> list = shopService.loadAll();
		mav.addObject("listShops", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("shopForm") Shop shop, BindingResult result) {
		if (!result.hasErrors()) {
			shopService.save(shop);
		}
		return "redirect:/shop/search";
	}

	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@ModelAttribute("shop/edit") Shop shop, @PathVariable("code") String code) {
		ModelAndView modelAndView = new ModelAndView("shop/shop");

		Shop shoploaded = shopService.load(code);

		modelAndView.addObject("shopForm", shoploaded);
		return modelAndView;
	}
}
