package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Author;

@Component
public class AuthorFormatter implements Formatter<Author> {

	@Override
	public String display(Author author) {
		return author.getFirstName() + " " + author.getLastName();
	}

}
