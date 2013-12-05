package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.dao.jpa.PublisherDao;
import org.telosys.starterkits.dao.repository.PublisherRepository;
import org.telosys.starterkits.service.PublisherService;

/**
 * Service : Publisher.
 */
@Component
@Transactional
public class PublisherServiceImpl implements PublisherService {
		
	@Resource
	private PublisherDao publisherDao;
	@Resource
	private PublisherRepository publisherRepository;
	
	public Publisher load(final Integer code) {
		return publisherRepository.findOne(code);
	}
	
	public Publisher save(final Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	public void delete(final Integer code) {
		publisherRepository.delete(code);
	}

	public List<Publisher> search(final Map<String,Object> criteria) {
		return publisherDao.search(criteria);
	}

	public List<Publisher> loadAll() {
		List<Publisher> publishers = new ArrayList<Publisher>();
		for (Publisher publisher : publisherRepository.findAll()) {
			publishers.add(publisher);
		}
		return publishers;
	}
	
	@Transactional(readOnly=true)
	public Page<Publisher> findAllByPage(Pageable pageable) {
		return publisherRepository.findAll(pageable);
	}
	
}
