package org.telosys.starterkits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telosys.starterkits.bean.BookOrder;
import org.telosys.starterkits.dao.jpa.BookOrderDao;
import org.telosys.starterkits.dao.repository.BookOrderRepository;
import org.telosys.starterkits.service.BookOrderService;

/**
 * Service : BookOrder.
 */
@Component
@Transactional
public class BookOrderServiceImpl implements BookOrderService {
		
	@Resource
	private BookOrderDao bookorderDao;
	@Resource
	private BookOrderRepository bookorderRepository;
	
	public BookOrder load(final Integer id) {
		return bookorderRepository.findOne(id);
	}
	
	public BookOrder save(final BookOrder bookorder) {
		return bookorderRepository.save(bookorder);
	}

	public void delete(final Integer id) {
		bookorderRepository.delete(id);
	}

	public List<BookOrder> search(final Map<String,Object> criteria) {
		return bookorderDao.search(criteria);
	}

	public List<BookOrder> loadAll() {
		List<BookOrder> bookorders = new ArrayList<BookOrder>();
		for (BookOrder bookorder : bookorderRepository.findAll()) {
			bookorders.add(bookorder);
		}
		return bookorders;
	}
	
}
