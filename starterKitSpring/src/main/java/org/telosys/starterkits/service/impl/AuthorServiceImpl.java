package org.telosys.starterkits.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.dao.jpa.AuthorDao;
import org.telosys.starterkits.service.AuthorService;

/**
 * Service : Author.
 */
@Component
public class AuthorServiceImpl implements AuthorService {
		
	@Resource
	private AuthorDao authorDao;
	
	public Author load(final Integer id) {
		return authorDao.load(id);
	}
	
	public Author save(final Author author) {
		return authorDao.save(author);
	}

	public void delete(final Integer id) {
		authorDao.delete(id);
	}

	public List<Author> search(final Map<String,Object> criteria) {
		return authorDao.search(criteria);
	}

	public List<Author> loadAll() {
		return authorDao.loadAll();
	}
	
}
