package org.oxerr.freeradius.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "radpostauth")
public class RadPostAuth implements Serializable {

	private static final long serialVersionUID = 2016101001L;

	private long id;
	private String userName;
	private String pass;
	private String reply;
	private String calledStationId;
	private String callingStationId;
	private OffsetDateTime authDate;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "pass")
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Column(name = "reply")
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@Column(name = "calledstationid")
	public String getCalledStationId() {
		return calledStationId;
	}

	public void setCalledStationId(String calledStationId) {
		this.calledStationId = calledStationId;
	}

	@Column(name = "callingstationid")
	public String getCallingStationId() {
		return callingStationId;
	}

	public void setCallingStationId(String callingStationId) {
		this.callingStationId = callingStationId;
	}

	@Column(name = "authdate")
	public OffsetDateTime getAuthDate() {
		return authDate;
	}

	public void setAuthDate(OffsetDateTime authDate) {
		this.authDate = authDate;
	}

}
