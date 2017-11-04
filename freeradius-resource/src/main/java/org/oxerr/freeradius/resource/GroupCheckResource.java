package org.oxerr.freeradius.resource;

import javax.annotation.Nullable;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.oxerr.commons.ws.rs.data.OffsetPageRequest;
import org.oxerr.freeradius.domain.RadGroupCheck;
import org.oxerr.freeradius.domain.Role;
import org.oxerr.freeradius.service.RadGroupCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * Endpoints for accessing group check data.
 */
@Path("/group-check")
@Component
public class GroupCheckResource {

	private final RadGroupCheckService radGroupCheckService;

	@Autowired
	public GroupCheckResource(RadGroupCheckService radGroupCheckService) {
		this.radGroupCheckService = radGroupCheckService;
	}

	/**
	 * Gets the {@link RadGroupCheck}s of the specified group.
	 *
	 * @param groupName the group name.
	 * @param pageable the pagination information.
	 * @return the {@link RadGroupCheck}s of the specified group.
	 */
	@GET @Path("/{groupName}")
	@RolesAllowed({ Role.ROLE_STAFF })
	public Page<RadGroupCheck> getRadGroupChecks(
		@PathParam("groupName") String groupName,
		@BeanParam OffsetPageRequest pageable
	) {
		return radGroupCheckService.getRadGroupChecks(groupName, pageable);
	}

	/**
	 * Gets {@link RadGroupCheck}.
	 *
	 * @param groupName the group name.
	 * @param attribute the attribute name.
	 * @return the {@link RadGroupCheck}.
	 */
	@GET @Path("/{groupName}/{attribute}")
	@RolesAllowed({ Role.ROLE_STAFF })
	public RadGroupCheck getRadGroupCheck(
		@PathParam("groupName") String groupName,
		@PathParam("attribute") String attribute
	) {
		return radGroupCheckService.getRadGroupCheck(groupName, attribute);
	}

	/**
	 * Sets {@link RadGroupCheck}.
	 *
	 * @param groupName the group name.
	 * @param attribute the attribute.
	 * @param op the operator.
	 * When creating, default is {@value RadGroupCheck#DEFAULT_OP}.
	 * When amending, {@code null} means do not change the operator but amend the value only.
	 * @param value the value.
	 * {@code null} means delete the {@link RadGroupCheck} as same as the {@link DELETE} method.
	 * @return the saved {@link RadGroupCheck}.
	 */
	@PUT @Path("/{groupName}/{attribute}")
	@RolesAllowed(Role.ROLE_STAFF)
	public RadGroupCheck saveRadGroupCheck(
		@PathParam("groupName") String groupName,
		@PathParam("attribute") String attribute,
		@Nullable @FormParam("op") String op,
		@Nullable @FormParam("value") String value
	) {
		return radGroupCheckService.saveAttribute(groupName, attribute, op, value);
	}

	/**
	 * Deletes {@link RadGroupCheck}.
	 *
	 * @param groupName the group name.
	 * @param attribute the attribute.
	 */
	@DELETE @Path("/{groupName}/{attribute}")
	@RolesAllowed(Role.ROLE_STAFF)
	public void deleteRadGroupCheck(
		@PathParam("groupName") String groupName,
		@PathParam("attribute") String attribute
	) {
		radGroupCheckService.deleteAttribute(groupName, attribute);
	}

}
