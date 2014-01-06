package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Publisher;

@Component
public class PublisherFormatter implements Formatter<Publisher> {

	@Override
	public String display(Publisher publisher) {
		return publisher.toString();
	}

}
