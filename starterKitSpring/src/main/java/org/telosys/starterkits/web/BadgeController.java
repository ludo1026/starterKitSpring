package org.telosys.starterkits.web;

import java.util.List;
import java.util.Date;

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
import org.telosys.starterkits.bean.Badge;
import org.telosys.starterkits.service.BadgeService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;
import org.telosys.starterkits.web.helper.MessageHelper;

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
	@Resource
	private MessageHelper messageHelper;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Badge> list = badgeService.loadAll();
		uiModel.addAttribute("listBadges", list);
		return "badge/list";
	}

	void populateForm(Model uiModel, Badge badge) {
		uiModel.addAttribute("badge", badge);
		// Listes déroulantes des objets liés
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Badge());
		return "badge/create";
	}

	@RequestMapping(value = "/{badgeNumber}")
	public String displayEditForm(Model uiModel, @PathVariable("badgeNumber") Integer badgeNumber) {
		Badge badge = badgeService.load(badgeNumber);
		this.populateForm(uiModel, badge);
		return "badge/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Badge badge, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				badge = badgeService.save(badge);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/badge/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, badge.getBadgeNumber());
			} else {
				populateForm(uiModel, badge);
				return "badge/create";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "badge.error.create", e);
			populateForm(uiModel, badge);
			return "badge/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Badge badge, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				badge = badgeService.save(badge);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/badge/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, badge.getBadgeNumber());
			} else {
				populateForm(uiModel, badge);
				return "badge/edit";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "badge.error.update", e);
			populateForm(uiModel, badge);
			return "badge/edit";
		}
	}

	@RequestMapping(value = "/delete/{badgeNumber}")
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("badgeNumber") Integer badgeNumber) {
		try {
			badgeService.delete(badgeNumber);
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "badge.error.delete", e);
		}
		return "redirect:/badge";
	}
	
}
