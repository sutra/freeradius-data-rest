package org.oxerr.freeradius.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.oxerr.commons.ws.rs.OffsetPageRequest;
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
	 * Returns the {@link RadGroupCheck}s of the specified group.
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
	 * Returns the {@link RadGroupCheck} of the specified group and attribute.
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

}
