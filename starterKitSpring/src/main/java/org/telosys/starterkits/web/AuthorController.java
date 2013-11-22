/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:31 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.telosys.starterkits.bean.Author;

import org.telosys.starterkits.service.AuthorService;

/**
 * Author.
 */
@Controller
@RequestMapping("/author*")
public class AuthorController 
{
    private AuthorService authorService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("author/author", "command", new  Author());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showAuthors() {
		authorService = new AuthorService();
		ModelAndView mav = new ModelAndView("author/authorList");
		List<Author> list = authorService.loadAll();
		mav.addObject("listAuthors", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("authorForm") Author author, BindingResult result) {
		authorService = new AuthorService();
		if (!result.hasErrors()) {
			authorService.save(author);
		}
		return "redirect:/author/search";
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute("author/edit") Author author, @PathVariable("id") Integer id) {
		authorService = new AuthorService();
		ModelAndView modelAndView = new ModelAndView("author/author");

		Author authorloaded = authorService.load(id);

		modelAndView.addObject("current", authorloaded);
		return modelAndView;
	}
}
