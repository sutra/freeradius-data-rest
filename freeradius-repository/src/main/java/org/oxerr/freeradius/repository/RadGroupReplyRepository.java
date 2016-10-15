package org.oxerr.freeradius.repository;

import org.oxerr.freeradius.domain.RadGroupReply;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RadGroupReplyRepository
		extends CrudRepository<RadGroupReply, Long>,
		JpaSpecificationExecutor<RadGroupReply>,
		QueryDslPredicateExecutor<RadGroupReply> {

}
