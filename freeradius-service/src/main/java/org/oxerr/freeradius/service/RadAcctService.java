package org.oxerr.freeradius.service;

import java.time.OffsetDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.security.RolesAllowed;

import org.oxerr.freeradius.domain.RadAcct;
import org.oxerr.freeradius.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RadAcctService {

	@RolesAllowed(value = "ROLE_ADMIN")
	Page<RadAcct> getRadAccts(@Nullable Pageable pageable);

	@PreAuthorize("#userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	Page<RadAcct> getRadAccts(
		@Nonnull String userName,
		@Nullable OffsetDateTime beginAcctStartTime,
		@Nullable OffsetDateTime endAcctStartTime,
		@Nullable Pageable pageable
	);

	@PreAuthorize("#userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	Long getTraffic(@Nonnull String userName);

	@PreAuthorize("#userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	Long getTraffic(@Nonnull String userName, @Nonnull OffsetDateTime acctStartTime);

	@PreAuthorize("#userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	Long getMaxMonthlyTraffic(@Nonnull String userName);

}
