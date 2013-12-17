package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return shopRepository.findOne(code);
	}
	
	public Shop save(final Shop shop) {
		return shopRepository.save(shop);
	}

	public void delete(final String code) {
		shopRepository.delete(code);
	}

	public List<Shop> search(final Map<String,Object> criteria) {
		return shopDao.search(criteria);
	}

	public List<Shop> loadAll() {
		List<Shop> shops = new ArrayList<Shop>();
		for (Shop shop : shopRepository.findAll()) {
			shops.add(shop);
		}
		return shops;
	}
	
	@Transactional(readOnly=true)
	public Page<Shop> findAllByPage(Pageable pageable) {
		return shopRepository.findAll(pageable);
	}
	
}
