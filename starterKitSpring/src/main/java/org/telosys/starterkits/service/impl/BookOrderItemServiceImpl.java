package org.telosys.starterkits.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
		return bookorderitemDao.load(id);
	}
	
	public BookOrderItem save(final BookOrderItem bookorderitem) {
		return bookorderitemDao.save(bookorderitem);
	}

	public void delete(final BookOrderItemId id) {
		bookorderitemDao.delete(id);
	}

	public List<BookOrderItem> search(final Map<String,Object> criteria) {
		return bookorderitemDao.search(criteria);
	}

	public List<BookOrderItem> loadAll() {
		return bookorderitemDao.loadAll();
	}
	
}
