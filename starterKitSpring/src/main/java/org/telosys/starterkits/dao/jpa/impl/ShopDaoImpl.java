package org.telosys.starterkits.dao.jpa.impl;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Shop;
import org.telosys.starterkits.dao.jpa.ShopDao;
import org.telosys.starterkits.dao.jpa.impl.base.DaoImpl;

/**
 * DAO : Shop.
 */
@Component
public class ShopDaoImpl extends DaoImpl<Shop, String> implements ShopDao {

	/**
	 * Constructor.
	 */
	public ShopDaoImpl() {
		super(Shop.class);
	}

}
