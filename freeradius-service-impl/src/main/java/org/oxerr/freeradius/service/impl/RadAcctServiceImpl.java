package org.oxerr.freeradius.service.impl;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oxerr.freeradius.domain.QRadAcct;
import org.oxerr.freeradius.domain.RadAcct;
import org.oxerr.freeradius.repository.RadAcctRepository;
import org.oxerr.freeradius.service.CheckService;
import org.oxerr.freeradius.service.RadAcctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

@Service
public class RadAcctServiceImpl implements RadAcctService {

	private static final long MAX_MONTHLY_TRAFFIC_BASE = 1024 * 1024; // MiB

	private final Logger log = LogManager.getLogger();
	private final QRadAcct qRadAcct = QRadAcct.radAcct;
	private final RadAcctRepository radAcctRepository;
	private final CheckService checkService;

	@Autowired
	public RadAcctServiceImpl(
		RadAcctRepository radAcctRepository,
		CheckService checkService
	) {
		this.radAcctRepository = radAcctRepository;
		this.checkService = checkService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<RadAcct> getRadAccts(Pageable pageable) {
		return radAcctRepository.findAll((Specification<RadAcct>) null,
				pageable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<RadAcct> getRadAccts(
		String userName,
		OffsetDateTime beginAcctStartTime,
		OffsetDateTime endAcctStartTime,
		Pageable pageable
	) {

		Predicate predicate = ExpressionUtils.allOf(
			qRadAcct.userName.eq(userName),
			qRadAcct.acctStartTime.goe(Optional.ofNullable(beginAcctStartTime).orElseGet(() -> beginningOfThisMonth())),
			qRadAcct.acctStartTime.loe(Optional.ofNullable(endAcctStartTime).orElseGet(() -> OffsetDateTime.now()))
		);
		return radAcctRepository.findAll(predicate, pageable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getTraffic(String userName) {
		return getTraffic(userName, beginningOfThisMonth());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getTraffic(String userName, OffsetDateTime acctStartTime) {
		return radAcctRepository.getTraffic(userName, acctStartTime);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getMaxMonthlyTraffic(String userName) {
		final String attribute = "Max-Monthly-Traffic";
		final Long maxMonthlyTraffic = checkService.getMinLongAttribute(userName, attribute);
		return maxMonthlyTraffic == null ? 0 : maxMonthlyTraffic * MAX_MONTHLY_TRAFFIC_BASE;
	}

	private OffsetDateTime beginningOfThisMonth() {
		final OffsetDateTime begin = OffsetDateTime.now(ZoneOffset.UTC)
			.withDayOfMonth(1)
			.withHour(0)
			.withMinute(0)
			.withSecond(0)
			.withNano(0);
		log.debug("begin: {}", begin);
		return begin;
	}

}
