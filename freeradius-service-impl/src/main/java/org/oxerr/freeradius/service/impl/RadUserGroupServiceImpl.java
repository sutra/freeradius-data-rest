package org.oxerr.freeradius.service.impl;

import org.oxerr.freeradius.domain.QRadUserGroup;
import org.oxerr.freeradius.domain.RadUserGroup;
import org.oxerr.freeradius.domain.RadUserGroupId;
import org.oxerr.freeradius.repository.RadUserGroupRepository;
import org.oxerr.freeradius.service.RadUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class RadUserGroupServiceImpl implements RadUserGroupService {

	private final QRadUserGroup qRadUserGroup = QRadUserGroup.radUserGroup;
	private final RadUserGroupRepository radUserGroupRepository;

	@Autowired
	public RadUserGroupServiceImpl(RadUserGroupRepository radUserGroupRepository) {
		this.radUserGroupRepository = radUserGroupRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<RadUserGroup> getRadUserGroups(String userName) {
		final Sort sort = new Sort(Direction.DESC, "priority");
		return radUserGroupRepository.findAll(qRadUserGroup.userName.eq(userName), sort);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadUserGroup saveRadUserGroup(RadUserGroup radUserGroup) {
		return radUserGroupRepository.save(radUserGroup);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadUserGroup addUserGroup(String userName, String groupName) {
		return saveRadUserGroup(new RadUserGroup(userName, groupName));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadUserGroup addUserGroup(String userName, String groupName,
			int priority) {
		return saveRadUserGroup(new RadUserGroup(userName, groupName, priority));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUserGroup(String userName, String groupName) {
		radUserGroupRepository.delete(new RadUserGroupId(userName, groupName));
	}

}
