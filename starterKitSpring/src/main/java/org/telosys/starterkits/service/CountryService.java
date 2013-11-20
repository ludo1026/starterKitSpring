/*
 * Service class 
 * Created on 20 nov. 2013 ( Time 11:56:30 )
 */

package org.telosys.starterkits.service;

import java.util.List;

import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.dao.CountryDAO;
import org.telosys.starterkits.springjpa.EntityManagerHelper;
import org.telosys.starterkits.springjpa.IService;
import javax.persistence.PersistenceException;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


public class CountryService implements IService<Country, String> {

	protected final Logger LOG = LoggerFactory.getLogger(CountryService.class);

	public Country load(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Country country;
		try {
			EntityManagerHelper.beginTransaction();
			JpaCountryDAO countryDAO = new JpaCountryDAO();
			country = countryDAO.findById(code);
			EntityManagerHelper.commitAndCloseEntityManager();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
		return country ;
	}

	public Country save(final Country entity) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Country entityNew;
		try {
			EntityManagerHelper.beginTransaction();
			JpaCountryDAO countryDAO = new JpaCountryDAO();
			entityNew = countryDAO.update(entity);
			EntityManagerHelper.commitAndCloseEntityManager();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
		return entityNew;
	}

	public void delete(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			EntityManagerHelper.beginTransaction();
			JpaCountryDAO countryDAO = new JpaCountryDAO();
			countryDAO.delete(code);
			EntityManagerHelper.commitAndCloseEntityManager();	
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
	}

	public List<Country> search(final Country country) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Country> liste;
		try {
			EntityManagerHelper.beginTransaction();
			JpaCountryDAO countryDAO = new JpaCountryDAO();
			liste = countryDAO.search(country);
			EntityManagerHelper.commitAndCloseEntityManager();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		} finally {
			if (EntityManagerHelper.isCloseEntityManager() == false) EntityManagerHelper.rollback();
		}
		return liste;
	}

	public List<Country> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Country> liste;
		try {
			EntityManagerHelper.beginTransaction();
			JpaCountryDAO countryDAO = new JpaCountryDAO();
			liste = countryDAO.loadAll();
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
