package org.oxerr.freeradius.repository;

import org.oxerr.freeradius.domain.RadCheck;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RadCheckRepository extends CrudRepository<RadCheck, Long>,
		JpaSpecificationExecutor<RadCheck>,
		QueryDslPredicateExecutor<RadCheck> {

}
