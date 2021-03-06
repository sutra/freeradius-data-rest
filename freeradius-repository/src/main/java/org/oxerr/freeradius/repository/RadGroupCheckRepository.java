package org.oxerr.freeradius.repository;

import org.oxerr.freeradius.domain.RadGroupCheck;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RadGroupCheckRepository
		extends CrudRepository<RadGroupCheck, Long>,
		JpaSpecificationExecutor<RadGroupCheck>,
		QueryDslPredicateExecutor<RadGroupCheck> {

}
