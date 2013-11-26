/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:07:42 )
 */

package org.telosys.starterkits.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

import org.telosys.starterkits.bean.Shop;
import org.telosys.starterkits.service.ShopService;

/**
 * Shop.
 */
@Controller
@RequestMapping("/shopForm")
public class ShopFormController 
{
	@Resource
    private ShopService shopService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("shopForm") Shop shop, BindingResult result) {
		if (!result.hasErrors()) {
			shopService.save(shop);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/shop/list";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(@ModelAttribute("shop/delete") Shop shop, @PathVariable("code") String code) {
		if (code != null){
			shopService.delete(code);
		}
		return "redirect:/shop/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("shopForm") Shop shop, BindingResult result) {
		ModelAndView mav = new ModelAndView("shop/shopList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Shop> list = shopService.search(criteria);
		mav.addObject("listshops", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("shop/shop", "shopForm", new Shop());
	}
}
