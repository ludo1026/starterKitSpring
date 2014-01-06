package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Country;

@Component
public class CountryFormatter implements Formatter<Country> {

	@Override
	public String display(Country country) {
		return country.getName();
	}

}
