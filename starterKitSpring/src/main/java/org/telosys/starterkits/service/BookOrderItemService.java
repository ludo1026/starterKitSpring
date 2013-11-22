/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:39 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.BookOrderItem;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.BookOrderItemPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import org.telosys.starterkits.bean.BookOrderItemId;

/**
 * Service : BookOrderItem.
 */
public class BookOrderItemService implements IService<BookOrderItem, BookOrderItemId> {

	protected final Logger LOG = LoggerFactory.getLogger(BookOrderItemService.class);
	
	private BookOrderItemPersistence getBookOrderItemPersistence() {
		return PersistenceServiceProvider.getService(BookOrderItemPersistence.class);
	}

	public BookOrderItem load(final BookOrderItemId id) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		BookOrderItem bookorderitem;
		try {
			BookOrderItemPersistence bookorderitemPersistence = getBookOrderItemPersistence();
			bookorderitem = bookorderitemPersistence.load(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return bookorderitem ;
	}

	public BookOrderItem save(final BookOrderItem bookorderitem) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		BookOrderItem bookorderitemSaved;
		try {
			BookOrderItemPersistence bookorderitemPersistence = getBookOrderItemPersistence();
			bookorderitemSaved = bookorderitemPersistence.save(bookorderitem);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return bookorderitemSaved;
	}

	public void delete(final BookOrderItemId id) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			BookOrderItemPersistence bookorderitemPersistence = getBookOrderItemPersistence();
			bookorderitemPersistence.delete(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<BookOrderItem> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<BookOrderItem> bookorderitems;
		try {
			BookOrderItemPersistence bookorderitemPersistence = getBookOrderItemPersistence();
			bookorderitems = bookorderitemPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return bookorderitems;
	}

	public List<BookOrderItem> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<BookOrderItem> bookorderitems;
		try {
			BookOrderItemPersistence bookorderitemPersistence = getBookOrderItemPersistence();
			bookorderitems = bookorderitemPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return bookorderitems;
	}

}
