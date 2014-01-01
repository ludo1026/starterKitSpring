package org.telosys.starterkits.web;

import java.util.List;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.telosys.starterkits.bean.Book;
import org.telosys.starterkits.service.BookService;
import org.telosys.starterkits.service.PublisherService;
import org.telosys.starterkits.service.AuthorService;
import org.telosys.starterkits.web.bean.Message;
import org.telosys.starterkits.web.bean.TypeMessage;
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
	@Resource
    private PublisherService publisherService;
	@Resource
    private AuthorService authorService;

	@RequestMapping()
	public String list(Model uiModel) {
		List<Book> list = bookService.loadAll();
		uiModel.addAttribute("listBooks", list);
		return "book/list";
	}

	void populateForm(Model uiModel, Book book) {
		uiModel.addAttribute("book", book);
		// Listes déroulantes des objets liés
    	uiModel.addAttribute("publishers", publisherService.loadAll());
    	uiModel.addAttribute("authors", authorService.loadAll());
	}

	@RequestMapping("/create")
	public String displayCreateForm(Model uiModel) {
		this.populateForm(uiModel, new Book());
		return "book/create";
	}

	@RequestMapping(value = "/{id}")
	public String displayEditForm(Model uiModel, @PathVariable("id") Integer id) {
		Book book = bookService.load(id);
		this.populateForm(uiModel, book);
		return "book/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Book book, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			book = bookService.save(book);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/book/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, book.getId());
		} else {
			populateForm(uiModel, book);
			return "book/create";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Book book, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		if (!result.hasErrors()) {
			book = bookService.save(book);
			redirectAttributes.addFlashAttribute("message", new Message(TypeMessage.SUCCESS,"save.ok"));
			return "redirect:/book/"+controllerHelper.encodeUrlPathSegments(httpServletRequest, book.getId());
		} else {
			populateForm(uiModel, book);
			return "book/edit";
		}
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model uiModel, @PathVariable("id") Integer id) {
		bookService.delete(id);
		return "redirect:/book";
	}
	
}
