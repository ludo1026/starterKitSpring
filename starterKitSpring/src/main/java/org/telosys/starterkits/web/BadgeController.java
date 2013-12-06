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
import org.telosys.starterkits.bean.Badge;
import org.telosys.starterkits.service.BadgeService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Badge.
 */
@Controller
@RequestMapping("/badge")
public class BadgeController 
{
	@Resource
    private BadgeService badgeService;
	@Resource
	private ControllerHelper controllerHelper;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Badge badge) {
		uiModel.addAttribute("badge", badge);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Badge());
		return "badge/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Badge> list = badgeService.loadAll();
		uiModel.addAttribute("listBadges", list);
		return "badge/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@Valid Badge badge, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			badge = badgeService.save(badge);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/badge/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, badge.getBadgeNumber());
		} else {
			return "badge/edit";
		}
	}

	@RequestMapping(value = "/{badgeNumber}")
	public String edit(Model uiModel, @PathVariable("badgeNumber") Integer badgeNumber) {
		Badge badge = badgeService.load(badgeNumber);
		this.populateEditForm(uiModel, badge);
		return "badge/edit";
	}

	@RequestMapping(value = "/delete/{badgeNumber}")
	public String delete(Model uiModel, @PathVariable("badgeNumber") Integer badgeNumber) {
		badgeService.delete(badgeNumber);
		return "redirect:/badge";
	}
	
}
