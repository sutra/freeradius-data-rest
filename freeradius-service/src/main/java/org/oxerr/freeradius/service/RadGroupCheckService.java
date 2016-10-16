package org.oxerr.freeradius.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.oxerr.freeradius.domain.RadGroupCheck;
import org.oxerr.freeradius.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RadGroupCheckService {

	Page<RadGroupCheck> getRadGroupChecks(@Nonnull String groupName, @Nullable Pageable pageable);

	RadGroupCheck getRadGroupCheck(@Nonnull String groupName, @Nonnull String attribute);

	@PreAuthorize("hasRole('" + Role.ROLE_STAFF + "')")
	RadGroupCheck saveRadGroupCheck(@Nonnull RadGroupCheck radGroupCheck);

	@PreAuthorize("hasRole('" + Role.ROLE_STAFF + "')")
	RadGroupCheck saveAttribute(
		@Nonnull String groupName,
		@Nonnull String attribute,
		@Nullable String op,
		@Nullable String value
	);

	@PreAuthorize("hasRole('" + Role.ROLE_STAFF + "')")
	void deleteAttribute(@Nonnull String groupName, @Nonnull String attribute);

}
