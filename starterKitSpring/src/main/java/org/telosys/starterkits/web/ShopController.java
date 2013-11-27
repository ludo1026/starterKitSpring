/*
 * Controller class 
 * Created on 27 nov. 2013 ( Time 18:10:07 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;

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

import org.telosys.starterkits.bean.Shop;

import org.telosys.starterkits.service.ShopService;

/**
 * Shop.
 */
@Controller
@RequestMapping("/shop")
public class ShopController 
{
	@Resource
    private ShopService shopService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Shop shop) {
		uiModel.addAttribute("shop", shop);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Shop());
		return "shop/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Shop> list = shopService.loadAll();
		uiModel.addAttribute("listShops", list);
		return "shop/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("shopForm") Shop shop, BindingResult result) {
		if (!result.hasErrors()) {
			shopService.save(shop);
		}
		return "redirect:/shop";
	}

	@RequestMapping(value = "/{code}")
	public String edit(Model uiModel, @PathVariable("code") String code) {
		Shop shop = shopService.load(code);
		this.populateEditForm(uiModel, shop);
		return "shop/edit";
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(Model uiModel, @PathVariable("code") String code) {
		shopService.delete(code);
		return "redirect:/shop";
	}
	
}
