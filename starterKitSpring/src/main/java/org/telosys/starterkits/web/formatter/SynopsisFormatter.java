package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Synopsis;

@Component
public class SynopsisFormatter implements Formatter<Synopsis> {

	@Override
	public String display(Synopsis synopsis) {
		return synopsis.toString();
	}

}
