package org.telosys.starterkits.web;

import java.util.List;
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
import org.telosys.starterkits.bean.Shop;
import org.telosys.starterkits.service.ShopService;
import org.telosys.starterkits.service.EmployeeService;
import org.telosys.starterkits.service.CountryService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;

/**
 * Shop.
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends AbstractController 
{
	@Resource
    private ShopService shopService;
	@Resource
    private EmployeeService employeeService;
	@Resource
    private CountryService countryService;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Shop> list = shopService.loadAll();
		uiModel.addAttribute("listShops", list);
		return "shop/list";
	}

	void populateForm(Model uiModel, Shop shop) {
		uiModel.addAttribute("shop", shop);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("employees", employeeService.loadAll());
    	uiModel.addAttribute("countrys", countryService.loadAll());
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Shop());
		return "shop/create";
	}

	@RequestMapping(value = "/{code}")
	public String displayEditForm(Model uiModel, @PathVariable("code") String code) {
		Shop shop = shopService.load(code);
		this.populateForm(uiModel, shop);
		return "shop/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Shop shop, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				if(shopService.load(shop.getCode()) != null) {
					result.addError(new ObjectError("shop", "already.exists"));
				}
			}
			if (!result.hasErrors()) {
				shop = shopService.save(shop);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/shop/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, shop.getCode());
			} else {
				populateForm(uiModel, shop);
				return "shop/create";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "shop.error.create", e);
			populateForm(uiModel, shop);
			return "shop/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Shop shop, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				shop = shopService.save(shop);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/shop/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, shop.getCode());
			} else {
				populateForm(uiModel, shop);
				return "shop/edit";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "shop.error.update", e);
			populateForm(uiModel, shop);
			return "shop/edit";
		}
	}

	@RequestMapping(value = "/delete/{code}")
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("code") String code) {
		try {
			shopService.delete(code);
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "shop.error.delete", e);
		}
		return "redirect:/shop";
	}
	
}
