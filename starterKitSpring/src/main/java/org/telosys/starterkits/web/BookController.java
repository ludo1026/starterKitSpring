/*
 * Controller class 
 * Created on 22 nov. 2013 ( Time 16:27:35 )
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

import org.telosys.starterkits.bean.Book;

import org.telosys.starterkits.service.BookService;

/**
 * Book.
 */
@Controller
@RequestMapping("/book*")
public class BookController 
{
    private BookService bookService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("book/book", "command", new  Book());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showBooks() {
		bookService = new BookService();
		ModelAndView mav = new ModelAndView("book/bookList");
		List<Book> list = bookService.loadAll();
		mav.addObject("listBooks", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookForm") Book book, BindingResult result) {
		bookService = new BookService();
		if (!result.hasErrors()) {
			bookService.save(book);
		}
		return "redirect:/book/search";
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute("book/edit") Book book, @PathVariable("id") Integer id) {
		bookService = new BookService();
		ModelAndView modelAndView = new ModelAndView("book/book");

		Book bookloaded = bookService.load(id);

		modelAndView.addObject("current", bookloaded);
		return modelAndView;
	}
}
