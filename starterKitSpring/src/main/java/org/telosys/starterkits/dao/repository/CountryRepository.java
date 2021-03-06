package org.telosys.starterkits.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.telosys.starterkits.bean.Country;

/**
 * Repository : Country.
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {

}
