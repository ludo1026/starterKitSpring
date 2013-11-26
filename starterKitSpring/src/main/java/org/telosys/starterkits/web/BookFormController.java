/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:06:11 )
 */

package org.telosys.starterkits.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
@RequestMapping("/bookForm")
public class BookFormController 
{
	@Resource
    private BookService bookService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookForm") Book book, BindingResult result) {
		if (!result.hasErrors()) {
			bookService.save(book);
		}else{
			System.out.println(result.getAllErrors().get(0).getDefaultMessage());
		}
		return "redirect:/book/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@ModelAttribute("book/delete") Book book, @PathVariable("id") Integer id) {
		if (id != null){
			bookService.delete(id);
		}
		return "redirect:/book/list";
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("bookForm") Book book, BindingResult result) {
		ModelAndView mav = new ModelAndView("book/bookList");
		Map<String, Object> criteria = new HashMap<String, Object>();
		// TODO Définir les critères
		List<Book> list = bookService.search(criteria);
		mav.addObject("listbooks", list);
		return mav;
	}
	
	@RequestMapping("/clear")
	public ModelAndView clear() {
		return new ModelAndView("book/book", "bookForm", new Book());
	}
}
