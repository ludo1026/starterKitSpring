/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:37 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.BookOrder;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.BookOrderPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


/**
 * Service : BookOrder.
 */
public class BookOrderService implements IService<BookOrder, Integer> {

	protected final Logger LOG = LoggerFactory.getLogger(BookOrderService.class);
	
	private BookOrderPersistence getBookOrderPersistence() {
		return PersistenceServiceProvider.getService(BookOrderPersistence.class);
	}

	public BookOrder load(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		BookOrder bookorder;
		try {
			BookOrderPersistence bookorderPersistence = getBookOrderPersistence();
			bookorder = bookorderPersistence.load(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return bookorder ;
	}

	public BookOrder save(final BookOrder bookorder) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		BookOrder bookorderSaved;
		try {
			BookOrderPersistence bookorderPersistence = getBookOrderPersistence();
			bookorderSaved = bookorderPersistence.save(bookorder);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return bookorderSaved;
	}

	public void delete(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			BookOrderPersistence bookorderPersistence = getBookOrderPersistence();
			bookorderPersistence.delete(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<BookOrder> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<BookOrder> bookorders;
		try {
			BookOrderPersistence bookorderPersistence = getBookOrderPersistence();
			bookorders = bookorderPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return bookorders;
	}

	public List<BookOrder> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<BookOrder> bookorders;
		try {
			BookOrderPersistence bookorderPersistence = getBookOrderPersistence();
			bookorders = bookorderPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return bookorders;
	}

}
