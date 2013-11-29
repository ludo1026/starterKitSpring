package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;
import org.telosys.starterkits.dao.jpa.BookOrderItemDao;
import org.telosys.starterkits.dao.repository.BookOrderItemRepository;
import org.telosys.starterkits.service.BookOrderItemService;

/**
 * Service : BookOrderItem.
 */
@Component
@Transactional
public class BookOrderItemServiceImpl implements BookOrderItemService {
		
	@Resource
	private BookOrderItemDao bookorderitemDao;
	@Resource
	private BookOrderItemRepository bookorderitemRepository;
	
	public BookOrderItem load(final BookOrderItemId id) {
		return bookorderitemRepository.findOne(id);
	}
	
	public BookOrderItem save(final BookOrderItem bookorderitem) {
		return bookorderitemRepository.save(bookorderitem);
	}

	public void delete(final BookOrderItemId id) {
		bookorderitemRepository.delete(id);
	}

	public List<BookOrderItem> search(final Map<String,Object> criteria) {
		return bookorderitemDao.search(criteria);
	}

	public List<BookOrderItem> loadAll() {
		List<BookOrderItem> bookorderitems = new ArrayList<BookOrderItem>();
		for (BookOrderItem bookorderitem : bookorderitemRepository.findAll()) {
			bookorderitems.add(bookorderitem);
		}
		return bookorderitems;
	}
	
	@Transactional(readOnly=true)
	public Page<BookOrderItem> findAllByPage(Pageable pageable) {
		return bookorderitemRepository.findAll(pageable);
	}
	
}
