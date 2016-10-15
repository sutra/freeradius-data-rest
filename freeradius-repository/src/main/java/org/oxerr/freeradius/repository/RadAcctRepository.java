package org.oxerr.freeradius.repository;

import java.time.OffsetDateTime;

import org.oxerr.freeradius.domain.RadAcct;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RadAcctRepository extends CrudRepository<RadAcct, Long>,
		JpaSpecificationExecutor<RadAcct>, QueryDslPredicateExecutor<RadAcct> {

	@Query("select sum(acctInputOctets + acctOutputOctets) as octets from RadAcct where userName = :userName and acctStartTime >= :acctStartTime")
	Long getTraffic(
		@Param("userName") String userName,
		@Param("acctStartTime") OffsetDateTime acctStartTime
	);

}
