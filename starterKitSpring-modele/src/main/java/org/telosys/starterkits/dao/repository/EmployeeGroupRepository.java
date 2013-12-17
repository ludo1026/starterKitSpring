package org.telosys.starterkits.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;

/**
 * Repository : EmployeeGroup.
 */
public interface EmployeeGroupRepository extends PagingAndSortingRepository<EmployeeGroup, EmployeeGroupId> {

}
