/*
 * Service class 
 * Created on 20 nov. 2013 ( Time 16:12:07 )
 */

package org.telosys.starterkits.service;

import java.util.List;

import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.dao.AuthorDAO;
import org.telosys.starterkits.springjpa.EntityManagerHelper;
import org.telosys.starterkits.springjpa.IService;
import javax.persistence.PersistenceException;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


public class AuthorService implements IService<Author, Integer> {

	protected final Logger LOG = LoggerFactory.getLogger(AuthorService.class);

	public Author load(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Author author;
		try {
			EntityManagerHelper.beginTransaction();
			AuthorDAO authorDAO = new AuthorDAO();
			author = authorDAO.findById(id);
			EntityManagerHelper.commitAndCloseEntityManager();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
		return author ;
	}

	public Author save(final Author entity) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Author entityNew;
		try {
			EntityManagerHelper.beginTransaction();
			AuthorDAO authorDAO = new AuthorDAO();
			entityNew = authorDAO.update(entity);
			EntityManagerHelper.commitAndCloseEntityManager();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
		return entityNew;
	}

	public void delete(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			EntityManagerHelper.beginTransaction();
			AuthorDAO authorDAO = new AuthorDAO();
			authorDAO.delete(id);
			EntityManagerHelper.commitAndCloseEntityManager();	
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
	}

	public List<Author> search(final Author author) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Author> liste;
		try {
			EntityManagerHelper.beginTransaction();
			AuthorDAO authorDAO = new AuthorDAO();
			liste = authorDAO.search(author);
			EntityManagerHelper.commitAndCloseEntityManager();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
		return liste;
	}

	public List<Author> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Author> liste;
		try {
			EntityManagerHelper.beginTransaction();
			AuthorDAO authorDAO = new AuthorDAO();
			liste = authorDAO.loadAll();
			EntityManagerHelper.commitAndCloseEntityManager();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
		return liste;
	}

}
