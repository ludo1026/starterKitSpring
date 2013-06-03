package org.telosys.starterkits.spring.author.controller;

import java.util.List;

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
import org.telosys.starterkits.spring.author.bean.Author;
import org.telosys.starterkits.spring.author.service.AuthorServices;



/**
 * Simple class to manage the author form.
 * <p><a href="AuthorFormController.java.html"><i>View Source</i></a>
 *
 * @author pbq
 */

@Controller
@RequestMapping("/authorform")
public class AuthorFormController {

	private AuthorServices authorServices;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}
    
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("authorform") Author author, BindingResult result) {
		authorServices = new AuthorServices();
		if (!result.hasErrors()) {
			authorServices.save(author);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/author/list";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("author/delete") Author author, @PathVariable("id") int id) {
		authorServices = new AuthorServices();
		if (id != 0){
			authorServices.delete(id);
		}
		return "redirect:/author/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("authorform") Author author, BindingResult result) {
		authorServices = new AuthorServices();
		ModelAndView mav = new ModelAndView("author/authorList");
		List<Author> list = authorServices.search(author);
		mav.addObject("listAuthors", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("author/author", "command", new Author());
	}
}
