package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Synopsis;
import org.telosys.starterkits.dao.jpa.SynopsisDao;
import org.telosys.starterkits.dao.repository.SynopsisRepository;
import org.telosys.starterkits.service.SynopsisService;

/**
 * Service : Synopsis.
 */
@Component
@Transactional
public class SynopsisServiceImpl implements SynopsisService {
		
	@Resource
	private SynopsisDao synopsisDao;
	@Resource
	private SynopsisRepository synopsisRepository;
	
	public Synopsis load(final Integer bookId) {
		return synopsisRepository.findOne(bookId);
	}
	
	public Synopsis save(final Synopsis synopsis) {
		return synopsisRepository.save(synopsis);
	}

	public void delete(final Integer bookId) {
		synopsisRepository.delete(bookId);
	}

	public List<Synopsis> search(final Map<String,Object> criteria) {
		return synopsisDao.search(criteria);
	}

	public List<Synopsis> loadAll() {
		List<Synopsis> synopsiss = new ArrayList<Synopsis>();
		for (Synopsis synopsis : synopsisRepository.findAll()) {
			synopsiss.add(synopsis);
		}
		return synopsiss;
	}
	
	@Transactional(readOnly=true)
	public Page<Synopsis> findAllByPage(Pageable pageable) {
		return synopsisRepository.findAll(pageable);
	}
	
}
