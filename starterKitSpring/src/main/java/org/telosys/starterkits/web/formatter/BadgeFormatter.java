package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Badge;

@Component
public class BadgeFormatter implements Formatter<Badge> {

	@Override
	public String display(Badge badge) {
		return badge.toString();
	}

}
