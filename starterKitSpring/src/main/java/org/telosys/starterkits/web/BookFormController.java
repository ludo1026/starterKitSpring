/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:35 )
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

import org.telosys.starterkits.bean.Book;
import org.telosys.starterkits.service.BookService;

/**
 * Book.
 */
@Controller
@RequestMapping("/bookform")
public class BookFormController 
{
    private BookService bookService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookform") Book book, BindingResult result) {
		bookService = new BookService();
		if (!result.hasErrors()) {
			bookService.save(book);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/book/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("book/delete") Book book, @PathVariable("id") Integer id) {
		bookService = new BookService();
		if (id != null){
			bookService.delete(id);
		}
		return "redirect:/book/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("bookform") Book book, BindingResult result) {
		bookService = new BookService();
		ModelAndView mav = new ModelAndView("book/bookList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Book> list = bookService.search(criteria);
		mav.addObject("listbooks", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("book/book", "command", new Book());
	}
}
