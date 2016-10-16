package org.oxerr.freeradius.resource;

import javax.annotation.Nonnull;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.oxerr.commons.ws.rs.OffsetPageRequest;
import org.oxerr.freeradius.domain.RadCheck;
import org.oxerr.freeradius.domain.RadUserGroup;
import org.oxerr.freeradius.domain.Role;
import org.oxerr.freeradius.service.CheckService;
import org.oxerr.freeradius.service.RadCheckService;
import org.oxerr.freeradius.service.RadUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * Endpoints for accessing user information.
 */
@Path("/user")
@Component
public class UserResource {

	private final RadCheckService radCheckService;
	private final RadUserGroupService radUserGroupService;
	private final CheckService checkService;

	@Autowired
	public UserResource(
		RadCheckService radCheckService,
		RadUserGroupService radUserGroupService,
		CheckService checkService
	) {
		this.radCheckService = radCheckService;
		this.radUserGroupService = radUserGroupService;
		this.checkService = checkService;
	}

	/**
	 * Create a new user.
	 *
	 * @param userName the user name of the new user.
	 * @param password the password of the new user.
	 * @return the created {@link RadCheck}.
	 */
	@POST @Path("/{userName}")
	@RolesAllowed({ Role.ROLE_STAFF })
	public RadCheck createUser(
		@PathParam("userName") String userName,
		@Nonnull @FormParam("password") String password
	) {
		return checkService.saveUser(userName, password);
	}

	/**
	 * Change password.
	 *
	 * @param userName the user name.
	 * @param password the new password.
	 * @return the updated {@link RadCheck}.
	 */
	@PUT @Path("/{userName}/password")
	@RolesAllowed({ Role.ROLE_USER, Role.ROLE_STAFF })
	public RadCheck savePassword(
		@PathParam("userName") String userName,
		@Nonnull @FormParam("password") String password
	) {
		return radCheckService.savePassword(userName, password);
	}

	/**
	 * Returns the groups which the specified user in.
	 *
	 * @param userName the user name.
	 * @return the groups.
	 */
	@GET @Path("/{userName}/group")
	@RolesAllowed({ Role.ROLE_USER, Role.ROLE_STAFF })
	public Page<RadUserGroup> getGroups(
		@PathParam("userName") String userName,
		@BeanParam OffsetPageRequest pageable
	) {
		return radUserGroupService.getRadUserGroups(userName, pageable);
	}

	/**
	 * Adds user to the specified group.
	 *
	 * @param userName the user name.
	 * @param groupName the group name.
	 * @param priority the priority.
	 * @return the added user group entity.
	 */
	@PUT @Path("/{userName}/group/{groupName}")
	@RolesAllowed(Role.ROLE_STAFF)
	public RadUserGroup addToGroup(
		@PathParam("userName") String userName,
		@PathParam("groupName") String groupName,
		@QueryParam("priority") int priority
	) {
		return radUserGroupService.addUserGroup(userName, groupName, priority);
	}

	/**
	 * Removes user from the specified group.
	 *
	 * @param userName the user name.
	 * @param groupName the group name.
	 */
	@DELETE @Path("/{userName}/group/{groupName}")
	@RolesAllowed(Role.ROLE_STAFF)
	public void removeFromGroup(
		@PathParam("userName") String userName,
		@PathParam("groupName") String groupName
	) {
		radUserGroupService.deleteUserGroup(userName, groupName);
	}

}
