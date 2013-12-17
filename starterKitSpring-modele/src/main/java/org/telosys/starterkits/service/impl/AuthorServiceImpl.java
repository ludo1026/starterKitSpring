package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.dao.jpa.AuthorDao;
import org.telosys.starterkits.dao.repository.AuthorRepository;
import org.telosys.starterkits.service.AuthorService;

/**
 * Service : Author.
 */
@Component
@Transactional
public class AuthorServiceImpl implements AuthorService {
		
	@Resource
	private AuthorDao authorDao;
	@Resource
	private AuthorRepository authorRepository;
	
	public Author load(final Integer id) {
		return authorRepository.findOne(id);
	}
	
	public Author save(final Author author) {
		return authorRepository.save(author);
	}

	public void delete(final Integer id) {
		authorRepository.delete(id);
	}

	public List<Author> search(final Map<String,Object> criteria) {
		return authorDao.search(criteria);
	}

	public List<Author> loadAll() {
		List<Author> authors = new ArrayList<Author>();
		for (Author author : authorRepository.findAll()) {
			authors.add(author);
		}
		return authors;
	}
	
	@Transactional(readOnly=true)
	public Page<Author> findAllByPage(Pageable pageable) {
		return authorRepository.findAll(pageable);
	}
	
}
