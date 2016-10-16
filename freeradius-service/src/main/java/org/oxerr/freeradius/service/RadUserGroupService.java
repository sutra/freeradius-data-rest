package org.oxerr.freeradius.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.oxerr.freeradius.domain.RadUserGroup;
import org.oxerr.freeradius.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RadUserGroupService {

	@PreAuthorize("#userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	Iterable<RadUserGroup> getRadUserGroups(@Nonnull String userName);

	@PreAuthorize("#userName == principal.username or hasRole('" + Role.ROLE_STAFF + "')")
	Page<RadUserGroup> getRadUserGroups(@Nonnull String userName, @Nullable Pageable pageable);

	RadUserGroup saveRadUserGroup(@Nonnull RadUserGroup radUserGroup);

	RadUserGroup addUserGroup(@Nonnull String userName, @Nonnull String groupName);
	RadUserGroup addUserGroup(@Nonnull String userName, @Nonnull String groupName, int priority);

	void deleteUserGroup(@Nonnull String userName, @Nonnull String groupName);

}
