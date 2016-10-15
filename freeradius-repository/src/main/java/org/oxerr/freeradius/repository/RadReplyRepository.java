package org.oxerr.freeradius.repository;

import org.oxerr.freeradius.domain.RadReply;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RadReplyRepository extends CrudRepository<RadReply, Long>,
		JpaSpecificationExecutor<RadReply>,
		QueryDslPredicateExecutor<RadReply> {

}
