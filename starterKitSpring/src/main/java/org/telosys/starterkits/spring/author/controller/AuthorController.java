package org.telosys.starterkits.spring.author.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.telosys.starterkits.spring.author.bean.Author;
import org.telosys.starterkits.spring.author.service.AuthorServices;


/**
 * Simple class to retrieve a list of authors from the database.
 * <p/>
 * <p>
 * <a href="AuthorController.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author pbq
 */

@Controller
@RequestMapping("/author*")
public class AuthorController {

	private AuthorServices authorServices;
	
	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("author/author", "command", new Author());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showAuthors() {
		authorServices = new AuthorServices();
		ModelAndView mav = new ModelAndView("author/authorList");
		List<Author> list = authorServices.loadAll();
		mav.addObject("listAuthors", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("authorForm") Author author, BindingResult result) {
		authorServices = new AuthorServices();
		if (!result.hasErrors()) {
			authorServices.save(author);
		}
		return "redirect:/author/search";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute("author/edit") Author author, @PathVariable("id") int id) {
		authorServices = new AuthorServices();
		ModelAndView modelAndView = new ModelAndView("author/author");
		if (id != 0){
			Author authloaded = authorServices.load(id);
			modelAndView.addObject("current", authloaded);
		}
		return modelAndView;
	}
}
