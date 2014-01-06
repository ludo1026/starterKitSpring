package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.BookOrderItem;

@Component
public class BookOrderItemFormatter implements Formatter<BookOrderItem> {

	@Override
	public String display(BookOrderItem bookorderitem) {
		return bookorderitem.toString();
	}

}
