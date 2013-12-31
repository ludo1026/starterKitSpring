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
import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.service.AuthorService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Author.
 */
@Controller
@RequestMapping("/author")
public class AuthorController 
{
	@Resource
    private AuthorService authorService;
	@Resource
	private ControllerHelper controllerHelper;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Author> list = authorService.loadAll();
		uiModel.addAttribute("listAuthors", list);
		return "author/list";
	}

	void populateForm(Model uiModel, Author author) {
		uiModel.addAttribute("author", author);
		// Listes déroulantes des objets liés
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Author());
		return "author/create";
	}

	@RequestMapping(value = "/{id}")
	public String displayEditForm(Model uiModel, @PathVariable("id") Integer id) {
		Author author = authorService.load(id);
		this.populateForm(uiModel, author);
		return "author/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Author author, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			author = authorService.save(author);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/author/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, author.getId());
		} else {
			populateForm(uiModel, author);
			return "author/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Author author, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			author = authorService.save(author);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/author/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, author.getId());
		} else {
			populateForm(uiModel, author);
			return "author/edit";
		}
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model uiModel, @PathVariable("id") Integer id) {
		authorService.delete(id);
		return "redirect:/author";
	}
	
}
