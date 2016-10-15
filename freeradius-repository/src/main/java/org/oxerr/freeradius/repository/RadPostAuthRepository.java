package org.oxerr.freeradius.repository;

import org.oxerr.freeradius.domain.RadPostAuth;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RadPostAuthRepository
		extends CrudRepository<RadPostAuth, Long>,
		JpaSpecificationExecutor<RadPostAuth>,
		QueryDslPredicateExecutor<RadPostAuth> {

}
