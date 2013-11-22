/*
 * Service class 
 * Created on 22 nov. 2013 ( Time 16:27:53 )
 */

package org.telosys.starterkits.service;

import java.util.List;
import java.util.Map;

import org.telosys.starterkits.bean.Shop;
import javax.persistence.PersistenceException;
import org.telosys.starterkits.persistence.PersistenceServiceProvider;
import org.telosys.starterkits.service.IService;
import org.telosys.starterkits.persistence.services.ShopPersistence;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;


/**
 * Service : Shop.
 */
public class ShopService implements IService<Shop, String> {

	protected final Logger LOG = LoggerFactory.getLogger(ShopService.class);
	
	private ShopPersistence getShopPersistence() {
		return PersistenceServiceProvider.getService(ShopPersistence.class);
	}

	public Shop load(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("load");
		Shop shop;
		try {
			ShopPersistence shopPersistence = getShopPersistence();
			shop = shopPersistence.load(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return shop ;
	}

	public Shop save(final Shop shop) {
		if (LOG.isDebugEnabled()) LOG.debug("save");
		Shop shopSaved;
		try {
			ShopPersistence shopPersistence = getShopPersistence();
			shopSaved = shopPersistence.save(shop);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return shopSaved;
	}

	public void delete(final String code) {
		if (LOG.isDebugEnabled()) LOG.debug("delete");
		try {
			ShopPersistence shopPersistence = getShopPersistence();
			shopPersistence.delete(code);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
	}

	public List<Shop> search(final Map<String,Object> criteria) {
		if (LOG.isDebugEnabled()) LOG.debug("search");
		List<Shop> shops;
		try {
			ShopPersistence shopPersistence = getShopPersistence();
			shops = shopPersistence.search(criteria);
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return shops;
	}

	public List<Shop> loadAll() {
		if (LOG.isDebugEnabled()) LOG.debug("loadAll");
		List<Shop> shops;
		try {
			ShopPersistence shopPersistence = getShopPersistence();
			shops = shopPersistence.loadAll();
		} catch (PersistenceException ex) {
			LOG.error("Error", ex);
			throw ex;
		}
		return shops;
	}

}
