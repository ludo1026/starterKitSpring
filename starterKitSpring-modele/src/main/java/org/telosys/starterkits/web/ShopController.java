package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.Shop;
import org.telosys.starterkits.service.ShopService;
import org.telosys.starterkits.service.EmployeeService;
import org.telosys.starterkits.service.CountryService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Shop.
 */
@Controller
@RequestMapping("/shop")
public class ShopController 
{
	@Resource
    private ShopService shopService;
	@Resource
	private ControllerHelper controllerHelper;
	@Resource
    private EmployeeService employeeService;
	@Resource
    private CountryService countryService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Shop shop) {
		uiModel.addAttribute("shop", shop);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("employees", employeeService.loadAll());
    	uiModel.addAttribute("countrys", countryService.loadAll());
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
	public String save(@Valid Shop shop, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			shop = shopService.save(shop);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/shop/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, shop.getCode());
		} else {
			return "shop/edit";
		}
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
