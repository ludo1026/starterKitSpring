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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Author author) {
		uiModel.addAttribute("author", author);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Author());
		return "author/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Author> list = authorService.loadAll();
		uiModel.addAttribute("listAuthors", list);
		return "author/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@Valid Author author, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			author = authorService.save(author);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/author/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, author.getId());
		} else {
			return "author/edit";
		}
	}

	@RequestMapping(value = "/{id}")
	public String edit(Model uiModel, @PathVariable("id") Integer id) {
		Author author = authorService.load(id);
		this.populateEditForm(uiModel, author);
		return "author/edit";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model uiModel, @PathVariable("id") Integer id) {
		authorService.delete(id);
		return "redirect:/author";
	}
	
}
