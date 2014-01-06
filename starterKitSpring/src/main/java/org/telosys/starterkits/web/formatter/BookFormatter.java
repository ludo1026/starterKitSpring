package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Book;

@Component
public class BookFormatter implements Formatter<Book> {

	@Override
	public String display(Book book) {
		return book.getTitle();
	}

}
