package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Customer;

@Component
public class CustomerFormatter implements Formatter<Customer> {

	@Override
	public String display(Customer customer) {
		return customer.toString();
	}

}
