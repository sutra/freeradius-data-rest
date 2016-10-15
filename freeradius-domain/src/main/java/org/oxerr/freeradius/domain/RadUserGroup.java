package org.oxerr.freeradius.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "radusergroup")
@IdClass(RadUserGroupId.class)
public class RadUserGroup implements Serializable {

	private static final long serialVersionUID = 2016101001L;

	private String userName;
	private String groupName;
	private int priority;

	public RadUserGroup() {
	}

	public RadUserGroup(String userName, String groupName) {
		this(userName, groupName, 0);
	}

	public RadUserGroup(String userName, String groupName, int priority) {
		this.userName = userName;
		this.groupName = groupName;
		this.priority = priority;
	}

	@Id
	@AttributeOverrides({
		@AttributeOverride(name = "userName", column = @Column(name = "username")),
		@AttributeOverride(name = "groupName", column = @Column(name = "groupname"))
	})
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "priority")
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s@%s", getUserName(), getGroupName());
	}

}
