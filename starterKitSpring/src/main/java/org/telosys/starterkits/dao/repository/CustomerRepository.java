package org.telosys.starterkits.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.telosys.starterkits.bean.Customer;

/**
 * Repository : Customer.
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {

}
