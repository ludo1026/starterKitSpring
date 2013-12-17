package org.telosys.starterkits.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.telosys.starterkits.bean.Employee;

/**
 * Repository : Employee.
 */
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String> {

}
