package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.BookOrder;

@Component
public class BookOrderFormatter implements Formatter<BookOrder> {

	@Override
	public String display(BookOrder bookorder) {
		return bookorder.toString();
	}

}
