package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Badge;
import org.telosys.starterkits.dao.jpa.BadgeDao;
import org.telosys.starterkits.dao.repository.BadgeRepository;
import org.telosys.starterkits.service.BadgeService;

/**
 * Service : Badge.
 */
@Component
@Transactional
public class BadgeServiceImpl implements BadgeService {
		
	@Resource
	private BadgeDao badgeDao;
	@Resource
	private BadgeRepository badgeRepository;
	
	public Badge load(final Integer badgeNumber) {
		return badgeRepository.findOne(badgeNumber);
	}
	
	public Badge save(final Badge badge) {
		return badgeRepository.save(badge);
	}

	public void delete(final Integer badgeNumber) {
		badgeRepository.delete(badgeNumber);
	}

	public List<Badge> search(final Map<String,Object> criteria) {
		return badgeDao.search(criteria);
	}

	public List<Badge> loadAll() {
		List<Badge> badges = new ArrayList<Badge>();
		for (Badge badge : badgeRepository.findAll()) {
			badges.add(badge);
		}
		return badges;
	}
	
}
