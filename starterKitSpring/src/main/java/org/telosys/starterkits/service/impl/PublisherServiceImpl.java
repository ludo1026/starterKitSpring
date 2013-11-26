package org.telosys.starterkits.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.dao.jpa.PublisherDao;
import org.telosys.starterkits.service.PublisherService;

/**
 * Service : Publisher.
 */
@Component
public class PublisherServiceImpl implements PublisherService {
		
	@Resource
	private PublisherDao publisherDao;
	
	public Publisher load(final Integer code) {
		return publisherDao.load(code);
	}
	
	public Publisher save(final Publisher publisher) {
		return publisherDao.save(publisher);
	}

	public void delete(final Integer code) {
		publisherDao.delete(code);
	}

	public List<Publisher> search(final Map<String,Object> criteria) {
		return publisherDao.search(criteria);
	}

	public List<Publisher> loadAll() {
		return publisherDao.loadAll();
	}
	
}
