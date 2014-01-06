package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.EmployeeGroup;

@Component
public class EmployeeGroupFormatter implements Formatter<EmployeeGroup> {

	@Override
	public String display(EmployeeGroup employeegroup) {
		return employeegroup.toString();
	}

}
