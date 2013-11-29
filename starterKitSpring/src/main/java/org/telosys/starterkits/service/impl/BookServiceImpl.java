package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Book;
import org.telosys.starterkits.dao.jpa.BookDao;
import org.telosys.starterkits.dao.repository.BookRepository;
import org.telosys.starterkits.service.BookService;

/**
 * Service : Book.
 */
@Component
@Transactional
public class BookServiceImpl implements BookService {
		
	@Resource
	private BookDao bookDao;
	@Resource
	private BookRepository bookRepository;
	
	public Book load(final Integer id) {
		return bookRepository.findOne(id);
	}
	
	public Book save(final Book book) {
		return bookRepository.save(book);
	}

	public void delete(final Integer id) {
		bookRepository.delete(id);
	}

	public List<Book> search(final Map<String,Object> criteria) {
		return bookDao.search(criteria);
	}

	public List<Book> loadAll() {
		List<Book> books = new ArrayList<Book>();
		for (Book book : bookRepository.findAll()) {
			books.add(book);
		}
		return books;
	}
	
}
