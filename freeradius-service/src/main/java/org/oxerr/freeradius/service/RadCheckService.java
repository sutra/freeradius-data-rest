package org.oxerr.freeradius.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.oxerr.freeradius.domain.RadCheck;
import org.oxerr.freeradius.domain.Role;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RadCheckService {

	@PreAuthorize("#userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	RadCheck getRadCheck(@Nonnull String userName, @Nonnull String attribute);

	Iterable<RadCheck> getRadChecks(@Nonnull String userName);

	@PreAuthorize("#radCheck.userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	RadCheck saveRadCheck(@Nonnull RadCheck radCheck);

	@PreAuthorize("#userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	RadCheck saveAttribute(
		@Nonnull String userName,
		@Nonnull String attribute,
		@Nullable String op,
		@Nullable String value
	);

	void deleteRadChecks(@Nonnull String userName);

	@PreAuthorize("#userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	RadCheck savePassword(@Nonnull String userName, @Nonnull String password);

}
