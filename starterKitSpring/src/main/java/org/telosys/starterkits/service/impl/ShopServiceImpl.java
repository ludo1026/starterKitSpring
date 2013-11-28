package org.telosys.starterkits.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Shop;
import org.telosys.starterkits.dao.jpa.ShopDao;
import org.telosys.starterkits.dao.repository.ShopRepository;
import org.telosys.starterkits.service.ShopService;

/**
 * Service : Shop.
 */
@Component
@Transactional
public class ShopServiceImpl implements ShopService {
		
	@Resource
	private ShopDao shopDao;
	@Resource
	private ShopRepository shopRepository;
	
	public Shop load(final String code) {
		return shopDao.load(code);
	}
	
	public Shop save(final Shop shop) {
		return shopDao.save(shop);
	}

	public void delete(final String code) {
		shopDao.delete(code);
	}

	public List<Shop> search(final Map<String,Object> criteria) {
		return shopDao.search(criteria);
	}

	public List<Shop> loadAll() {
		return shopDao.loadAll();
	}
	
}
