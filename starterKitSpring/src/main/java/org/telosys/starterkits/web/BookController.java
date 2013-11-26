/*
 * Controller class 
 * Created on 26 nov. 2013 ( Time 16:06:11 )
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

import org.telosys.starterkits.bean.Book;

import org.telosys.starterkits.service.BookService;

/**
 * Book.
 */
@Controller
@RequestMapping("/book*")
public class BookController 
{
	@Resource
    private BookService bookService;

	@RequestMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("book/book", "bookForm", new  Book());
	}

	@RequestMapping(value = "/list")
	public ModelAndView showBooks() {
		ModelAndView mav = new ModelAndView("book/bookList");
		List<Book> list = bookService.loadAll();
		mav.addObject("listBooks", list);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("bookForm") Book book, BindingResult result) {
		if (!result.hasErrors()) {
			bookService.save(book);
		}
		return "redirect:/book/search";
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute("book/edit") Book book, @PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("book/book");

		Book bookloaded = bookService.load(id);

		modelAndView.addObject("bookForm", bookloaded);
		return modelAndView;
	}
}
