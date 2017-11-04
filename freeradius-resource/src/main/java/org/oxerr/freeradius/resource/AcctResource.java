package org.oxerr.freeradius.resource;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nullable;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oxerr.commons.ws.rs.data.OffsetPageRequest;
import org.oxerr.freeradius.domain.RadAcct;
import org.oxerr.freeradius.domain.Role;
import org.oxerr.freeradius.service.RadAcctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * Endpoints for accessing accounting data.
 */
@Path("/acct")
@RolesAllowed({ Role.ROLE_USER, Role.ROLE_STAFF })
@Component
public class AcctResource {

	private final Logger log = LogManager.getLogger();
	private final RadAcctService radAcctService;

	@Autowired
	public AcctResource(RadAcctService radAcctService) {
		this.radAcctService = radAcctService;
	}

	/**
	 * Returns the accounting detail data of the specified user.
	 *
	 * @param userName the user name.
	 * @param beginAcctStartTime the begin of accounting start time,
	 * the beginning of this month will be used if not specified.
	 * @param endAcctStartTime the end of the accounting start time,
	 * the current system time will be used if not specified.
	 * @param offsetPageRequest the pagination information.
	 * @return the accounting detail data.
	 */
	@GET @Path("/{userName}")
	public Page<RadAcct> getRadAccts(
		@PathParam("userName") String userName,
		@Nullable @QueryParam("beginAcctStartTime") OffsetDateTime beginAcctStartTime,
		@Nullable @QueryParam("endAcctStartTime") OffsetDateTime endAcctStartTime,
		@BeanParam OffsetPageRequest offsetPageRequest
	) {
		log.debug("userName: {}, beginAcctStartTime: {}, endAcctStartTime: {}.",
			userName, beginAcctStartTime, endAcctStartTime);
		return radAcctService.getRadAccts(userName, beginAcctStartTime, endAcctStartTime, offsetPageRequest);
	}

	/**
	 * Returns the traffic used this month of the specified user.
	 *
	 * @param userName the user name.
	 * @return the traffic used and the max monthly traffic.
	 */
	@GET @Path("/{userName}/traffic")
	public Map<String, Long> getTraffic(
		@PathParam("userName") String userName
	) {
		final Long traffic = radAcctService.getTraffic(userName);
		final Long maxMonthlyTraffic = radAcctService.getMaxMonthlyTraffic(userName);

		final Map<String, Long> ret = new HashMap<>();
		ret.put("traffic", Optional.ofNullable(traffic).orElse(0L));
		ret.put("maxMonthlyTraffic", Optional.ofNullable(maxMonthlyTraffic).orElse(0L));

		return ret;
	}

}
