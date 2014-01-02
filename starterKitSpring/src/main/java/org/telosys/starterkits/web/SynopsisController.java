package org.telosys.starterkits.web;

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
import org.telosys.starterkits.bean.Synopsis;
import org.telosys.starterkits.service.SynopsisService;
import org.telosys.starterkits.service.BookService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;
import org.telosys.starterkits.web.helper.MessageHelper;

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
    private BookService bookService;
	@Resource
	private ControllerHelper controllerHelper;
	@Resource
	private MessageHelper messageHelper;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Synopsis> list = synopsisService.loadAll();
		uiModel.addAttribute("listSynopsiss", list);
		return "synopsis/list";
	}

	void populateForm(Model uiModel, Synopsis synopsis) {
		uiModel.addAttribute("synopsis", synopsis);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("books", bookService.loadAll());
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Synopsis());
		return "synopsis/create";
	}

	@RequestMapping(value = "/{bookId}")
	public String displayEditForm(Model uiModel, @PathVariable("bookId") Integer bookId) {
		Synopsis synopsis = synopsisService.load(bookId);
		this.populateForm(uiModel, synopsis);
		return "synopsis/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Synopsis synopsis, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				if(synopsisService.load(synopsis.getBookId()) != null) {
					result.addError(new ObjectError("synopsis", "already.exists"));
				}
			}
			if (!result.hasErrors()) {
				synopsis = synopsisService.save(synopsis);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/synopsis/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, synopsis.getBookId());
			} else {
				populateForm(uiModel, synopsis);
				return "synopsis/create";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "synopsis.error.create", e);
			populateForm(uiModel, synopsis);
			return "synopsis/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Synopsis synopsis, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		try {
			if (!result.hasErrors()) {
				synopsis = synopsisService.save(synopsis);
				messageHelper.addMessage(redirectAttributes, new Message(TypeMessage.SUCCESS,"save.ok"));
				return "redirect:/synopsis/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, synopsis.getBookId());
			} else {
				populateForm(uiModel, synopsis);
				return "synopsis/edit";
			}
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "synopsis.error.update", e);
			populateForm(uiModel, synopsis);
			return "synopsis/edit";
		}
	}

	@RequestMapping(value = "/delete/{bookId}")
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("bookId") Integer bookId) {
		try {
			synopsisService.delete(bookId);
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "synopsis.error.delete", e);
		}
		return "redirect:/synopsis";
	}
	
}
