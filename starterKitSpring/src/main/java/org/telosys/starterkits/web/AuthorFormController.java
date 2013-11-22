/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:32 )
 */

package org.telosys.starterkits.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping("/authorform")
public class AuthorFormController 
{
    private AuthorService authorService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("authorform") Author author, BindingResult result) {
		authorService = new AuthorService();
		if (!result.hasErrors()) {
			authorService.save(author);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/author/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("author/delete") Author author, @PathVariable("id") Integer id) {
		authorService = new AuthorService();
		if (id != null){
			authorService.delete(id);
		}
		return "redirect:/author/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("authorform") Author author, BindingResult result) {
		authorService = new AuthorService();
		ModelAndView mav = new ModelAndView("author/authorList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Author> list = authorService.search(criteria);
		mav.addObject("listauthors", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("author/author", "command", new Author());
	}
}
