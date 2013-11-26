/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:05:46 )
 */

package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;

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
	@Resource
    private AuthorService authorService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("author/author", "authorForm", new  Author());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showAuthors() {
		ModelAndView mav = new ModelAndView("author/authorList");
		List<Author> list = authorService.loadAll();
		mav.addObject("listAuthors", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("authorForm") Author author, BindingResult result) {
		if (!result.hasErrors()) {
			authorService.save(author);
		}
		return "redirect:/author/search";
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute("author/edit") Author author, @PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("author/author");

		Author authorloaded = authorService.load(id);

		modelAndView.addObject("authorForm", authorloaded);
		return modelAndView;
	}
}
