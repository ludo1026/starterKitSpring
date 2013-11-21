/*
 * Service class 
 * Created on 21 nov. 2013 ( Time 15:37:32 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.persistence.services.AuthorPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


public class AuthorService implements IService<Author, Integer> {

	protected final Logger LOG = LoggerFactory.getLogger(AuthorService.class);

	private AuthorPersistence getAuthorPersistence() {
		AuthorPersistence authorPersistence = PersistenceServiceProvider.getService(AuthorPersistence.class);
		return authorPersistence;
	}

	public Author load(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Author author;
		try {
			AuthorPersistence authorPersistence = getAuthorPersistence();
			author = authorPersistence.load(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return author ;
	}

	public Author save(final Author author) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Author authorSaved;
		try {
			AuthorPersistence authorPersistence = getAuthorPersistence();
			authorSaved = authorPersistence.save(author);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return authorSaved;
	}

	public void delete(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			AuthorPersistence authorPersistence = getAuthorPersistence();
			authorPersistence.delete(id);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Author> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Author> authors;
		try {
			AuthorPersistence authorPersistence = getAuthorPersistence();
			authors = authorPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return authors;
	}

	public List<Author> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Author> authors;
		try {
			AuthorPersistence authorPersistence = getAuthorPersistence();
			authors = authorPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return authors;
	}

}
