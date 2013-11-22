/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:35 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Book;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.BookPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


/**
 * Service : Book.
 */
public class BookService implements IService<Book, Integer> {

	protected final Logger LOG = LoggerFactory.getLogger(BookService.class);
	
	private BookPersistence getBookPersistence() {
		return PersistenceServiceProvider.getService(BookPersistence.class);
	}

	public Book load(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Book book;
		try {
			BookPersistence bookPersistence = getBookPersistence();
			book = bookPersistence.load(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return book ;
	}

	public Book save(final Book book) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Book bookSaved;
		try {
			BookPersistence bookPersistence = getBookPersistence();
			bookSaved = bookPersistence.save(book);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return bookSaved;
	}

	public void delete(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			BookPersistence bookPersistence = getBookPersistence();
			bookPersistence.delete(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Book> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Book> books;
		try {
			BookPersistence bookPersistence = getBookPersistence();
			books = bookPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return books;
	}

	public List<Book> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Book> books;
		try {
			BookPersistence bookPersistence = getBookPersistence();
			books = bookPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return books;
	}

}
