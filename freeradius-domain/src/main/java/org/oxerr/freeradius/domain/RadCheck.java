package org.oxerr.freeradius.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "radcheck")
public class RadCheck implements Serializable {

	private static final long serialVersionUID = 2016101201L;

	public static final String DEFAULT_OP = "==";

	private long id;
	private String userName;
	private String attribute;
	private String op;
	private String value;

	public RadCheck() {
	}

	public RadCheck(String userName, String attribute, String op, String value) {
		this.userName = userName;
		this.attribute = attribute;
		this.op = op;
		this.value = value;
	}

	@Id
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "attribute")
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Column(name = "op")
	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	@Column(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s.%s %s %s",
			getUserName(), getAttribute(), getOp(), getValue());
	}

}
