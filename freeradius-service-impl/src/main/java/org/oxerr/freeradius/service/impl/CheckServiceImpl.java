package org.oxerr.freeradius.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oxerr.freeradius.domain.RadCheck;
import org.oxerr.freeradius.domain.RadGroupCheck;
import org.oxerr.freeradius.domain.RadUserGroup;
import org.oxerr.freeradius.domain.Role;
import org.oxerr.freeradius.service.CheckService;
import org.oxerr.freeradius.service.RadCheckService;
import org.oxerr.freeradius.service.RadGroupCheckService;
import org.oxerr.freeradius.service.RadReplyService;
import org.oxerr.freeradius.service.RadUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckServiceImpl implements CheckService {

	private final Logger log = LogManager.getLogger();
	private final RadUserGroupService radUserGroupService;
	private final RadCheckService radCheckService;
	private final RadGroupCheckService radGroupCheckService;
	private final RadReplyService radReplyService;

	@Autowired
	public CheckServiceImpl(
		RadUserGroupService radUserGroupService,
		RadCheckService radCheckService,
		RadGroupCheckService radGroupCheckService,
		RadReplyService radReplyService
	) {
		this.radUserGroupService = radUserGroupService;
		this.radCheckService = radCheckService;
		this.radGroupCheckService = radGroupCheckService;
		this.radReplyService = radReplyService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getMinLongAttribute(String userName, String attribute) {
		Long value = null;

		final RadCheck radCheck = radCheckService.getRadCheck(userName, attribute);
		log.debug("radCheck: {}", radCheck);

		if (radCheck != null) {
			value = Long.parseLong(radCheck.getValue());
		}

		for (RadUserGroup group : radUserGroupService.getRadUserGroups(userName)) {
			final RadGroupCheck radGroupCheck = radGroupCheckService.getRadGroupCheck(group.getGroupName(), attribute);
			log.debug("group: {}, radGroupCheck: {}", group, radGroupCheck);

			if (radGroupCheck != null) {
				final long groupValue = Long.parseLong(radGroupCheck.getValue());

				if (value == null || groupValue < value) {
					value = groupValue;
				}
			}
		}

		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadCheck saveUser(String userName, String password) {
		final RadCheck radCheck = radCheckService.savePassword(userName, password);
		radUserGroupService.addUserGroup(userName, Role.ROLE_USER);
		return radCheck;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteUser(String userName) {
		radUserGroupService.deleteUserGroups(userName);
		radCheckService.deleteRadChecks(userName);
		radReplyService.deleteRadReplies(userName);
	}

}
