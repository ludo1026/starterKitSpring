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
import org.telosys.starterkits.bean.Synopsis;
import org.telosys.starterkits.service.SynopsisService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Synopsis.
 */
@Controller
@RequestMapping("/synopsis")
public class SynopsisController 
{
	@Resource
    private SynopsisService synopsisService;
	@Resource
	private ControllerHelper controllerHelper;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Synopsis synopsis) {
		uiModel.addAttribute("synopsis", synopsis);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Synopsis());
		return "synopsis/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Synopsis> list = synopsisService.loadAll();
		uiModel.addAttribute("listSynopsiss", list);
		return "synopsis/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@Valid Synopsis synopsis, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			synopsis = synopsisService.save(synopsis);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/synopsis/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, synopsis.getBookId());
		} else {
			return "synopsis/edit";
		}
	}

	@RequestMapping(value = "/{bookId}")
	public String edit(Model uiModel, @PathVariable("bookId") Integer bookId) {
		Synopsis synopsis = synopsisService.load(bookId);
		this.populateEditForm(uiModel, synopsis);
		return "synopsis/edit";
	}

	@RequestMapping(value = "/delete/{bookId}")
	public String delete(Model uiModel, @PathVariable("bookId") Integer bookId) {
		synopsisService.delete(bookId);
		return "redirect:/synopsis";
	}
	
}
