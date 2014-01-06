package org.telosys.starterkits.web.formatter;

import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Employee;

@Component
public class EmployeeFormatter implements Formatter<Employee> {

	@Override
	public String display(Employee employee) {
		return employee.toString();
	}

}
