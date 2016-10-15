package org.oxerr.freeradius.repository;

import org.oxerr.freeradius.domain.RadUserGroup;
import org.oxerr.freeradius.domain.RadUserGroupId;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RadUserGroupRepository
		extends CrudRepository<RadUserGroup, RadUserGroupId>,
		JpaSpecificationExecutor<RadUserGroup>,
		QueryDslPredicateExecutor<RadUserGroup> {

}
