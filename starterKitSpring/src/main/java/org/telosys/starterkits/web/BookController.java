package org.telosys.starterkits.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.telosys.starterkits.web.helper.ControllerHelper;

/**
 * Book.
 */
@Controller
@RequestMapping("/book")
public class BookController 
{
	@Resource
    private BookService bookService;
	@Resource
	private ControllerHelper controllerHelper;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	void populateEditForm(Model uiModel, Book book) {
		uiModel.addAttribute("book", book);
		// Listes déroulantes des objets liés
		// uiModel.addAttribute("bases", Base.findAllBases());
	}

	@RequestMapping("/create")
	public String create(Model uiModel) {
		this.populateEditForm(uiModel, new Book());
		return "book/edit";
	}

	@RequestMapping()
	public String list(Model uiModel) {
		List<Book> list = bookService.loadAll();
		uiModel.addAttribute("listBooks", list);
		return "book/list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String save(@ModelAttribute("bookForm") Book book, BindingResult result, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			book = bookService.save(book);
			return "redirect:/book/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, book.getId());
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/{id}")
	public String edit(Model uiModel, @PathVariable("id") Integer id) {
		Book book = bookService.load(id);
		this.populateEditForm(uiModel, book);
		return "book/edit";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model uiModel, @PathVariable("id") Integer id) {
		bookService.delete(id);
		return "redirect:/book";
	}
	
}
