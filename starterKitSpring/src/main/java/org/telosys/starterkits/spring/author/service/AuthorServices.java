/*
 * Service class 
 * Created on 22 mai 2013 ( Time 15:26:18 )
 */

package org.telosys.starterkits.spring.author.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.telosys.starterkits.spring.author.bean.Author;
import org.telosys.starterkits.spring.author.dao.JpaAuthorDAO;
import org.telosys.starterkits.springjpa.EntityManagerHelper;
import org.telosys.starterkits.springjpa.IServices;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


public class AuthorServices implements IServices<Author, Integer> {

	protected final Logger LOG = LoggerFactory.getLogger(AuthorServices.class);

	/**
	 * Load an author
	 * @return the loaded author
	 */
	public Author load(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Author author;
		try {
			EntityManagerHelper.beginTransaction();
			JpaAuthorDAO authorDAO = new JpaAuthorDAO();
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

	/**
	 * Save an author
	 * @return the saved Author
	 */
	public Author save(final Author entity) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Author entityNew;
		try {
			EntityManagerHelper.beginTransaction();
			JpaAuthorDAO authorDAO = new JpaAuthorDAO();
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

	/**
	 * delete an author
	 */
	public void delete(final Integer id) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			EntityManagerHelper.beginTransaction();
			JpaAuthorDAO authorDAO = new JpaAuthorDAO();
			authorDAO.delete(id);
			EntityManagerHelper.commitAndCloseEntityManager();	
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
	}
	
	/**
	 * Search by Author
	 * @return List of Author
	 */
	public List<Author> search(final Author author) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Author> liste;
		try {
			EntityManagerHelper.beginTransaction();
			JpaAuthorDAO authorDAO = new JpaAuthorDAO();
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

	/**
	 * Load all Authors
	 * @return List of Authors
	 */
	public List<Author> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Author> liste;
		try {
			EntityManagerHelper.beginTransaction();
			JpaAuthorDAO authorDAO = new JpaAuthorDAO();
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
