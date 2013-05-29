package org.telosys.starterkits.spring.author.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.telosys.starterkits.spring.author.bean.Author;
import org.telosys.starterkits.spring.author.service.AuthorServices;



/**
 * <p><a href="AuthorFormController.java.html"><i>View Source</i></a>
 *
 * @author pbq
 */

@Controller
@RequestMapping("/authorform")
public class AuthorFormController {

	private AuthorServices authorServices = null;
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("authorForm") Author author, BindingResult result) {
		authorServices = new AuthorServices();
		if (!result.hasErrors()) {
			System.out.println(author.getFirstName());
			System.out.println(author.getLastName());
			System.out.println(author.getId());
			authorServices.save(author);
		}
		return "redirect:/author/search";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("author/delete") Author author, @PathVariable("id") int id) {
		authorServices = new AuthorServices();
		if (id != 0){
			authorServices.delete(id);
		}
		return "redirect:/author/search";
	}
}
