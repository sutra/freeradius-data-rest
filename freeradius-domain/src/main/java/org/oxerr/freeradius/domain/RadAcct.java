package org.oxerr.freeradius.domain;

import java.io.Serializable;
import java.net.InetAddress;
import java.time.OffsetDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "radacct", uniqueConstraints = @UniqueConstraint(columnNames = "acctuniqueid"))
@TypeDefs(value={
	@TypeDef(name = "inet", typeClass = com.github.thealchemist.pg_hibernate.InetAddressType.class)
})
public class RadAcct implements Serializable {

	private static final long serialVersionUID = 2016100701L;

	private long radAcctId;
	private String acctSessionId;
	private String acctUniqueId;
	private String userName;
	private String groupName;
	private String realm;
	private InetAddress nasIPAddress;
	private String nasPortId;
	private String nasPortType;
	private OffsetDateTime acctStartTime;
	private OffsetDateTime acctStopTime;
	private Long acctSessionTime;
	private String acctAuthentic;
	private String connectInfoStart;
	private String connectInfoStop;
	private Long acctInputOctets;
	private Long acctOutputOctets;
	private String calledStationId;
	private String callingStationId;
	private String acctTerminateCause;
	private String serviceType;
	private String xAscendSessionSvrKey;
	private String framedProtocol;
	private InetAddress framedIPAddress;
	private Integer acctStartDelay;
	private Integer acctStopDelay;

	@Id
	@Column(name = "radacctid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getRadAcctId() {
		return radAcctId;
	}

	public void setRadAcctId(long radAcctId) {
		this.radAcctId = radAcctId;
	}

	@Column(name = "acctsessionid")
	public String getAcctSessionId() {
		return acctSessionId;
	}

	public void setAcctSessionId(String acctSessionId) {
		this.acctSessionId = acctSessionId;
	}

	@Column(name = "acctuniqueid")
	public String getAcctUniqueId() {
		return acctUniqueId;
	}

	public void setAcctUniqueId(String acctUniqueId) {
		this.acctUniqueId = acctUniqueId;
	}

	@Column(name = "username")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "groupname")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "realm")
	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	@Column(name = "nasipaddress")
	@Type(type="inet")
	public InetAddress getNasIPAddress() {
		return nasIPAddress;
	}

	public void setNasIPAddress(InetAddress nasIPAddress) {
		this.nasIPAddress = nasIPAddress;
	}

	@Column(name = "nasportid")
	public String getNasPortId() {
		return nasPortId;
	}

	public void setNasPortId(String nasPortId) {
		this.nasPortId = nasPortId;
	}

	@Column(name = "nasporttype")
	public String getNasPortType() {
		return nasPortType;
	}

	public void setNasPortType(String nasPortType) {
		this.nasPortType = nasPortType;
	}

	@Column(name = "acctstarttime")
	public OffsetDateTime getAcctStartTime() {
		return acctStartTime;
	}

	public void setAcctStartTime(OffsetDateTime acctStartTime) {
		this.acctStartTime = acctStartTime;
	}

	@Column(name = "acctstoptime")
	public OffsetDateTime getAcctStopTime() {
		return acctStopTime;
	}

	public void setAcctStopTime(OffsetDateTime acctStopTime) {
		this.acctStopTime = acctStopTime;
	}

	@Column(name = "acctsessiontime")
	public Long getAcctSessionTime() {
		return acctSessionTime;
	}

	public void setAcctSessionTime(Long acctSessionTime) {
		this.acctSessionTime = acctSessionTime;
	}

	@Column(name = "acctauthentic")
	public String getAcctAuthentic() {
		return acctAuthentic;
	}

	public void setAcctAuthentic(String acctAuthentic) {
		this.acctAuthentic = acctAuthentic;
	}

	@Column(name = "connectinfo_start")
	public String getConnectInfoStart() {
		return connectInfoStart;
	}

	public void setConnectInfoStart(String connectInfoStart) {
		this.connectInfoStart = connectInfoStart;
	}

	@Column(name = "connectinfo_stop")
	public String getConnectInfoStop() {
		return connectInfoStop;
	}

	public void setConnectInfoStop(String connectInfoStop) {
		this.connectInfoStop = connectInfoStop;
	}

	@Column(name = "acctinputoctets")
	public Long getAcctInputOctets() {
		return acctInputOctets;
	}

	public void setAcctInputOctets(Long acctInputOctets) {
		this.acctInputOctets = acctInputOctets;
	}

	@Column(name = "acctoutputoctets")
	public Long getAcctOutputOctets() {
		return acctOutputOctets;
	}

	public void setAcctOutputOctets(Long acctOutputOctets) {
		this.acctOutputOctets = acctOutputOctets;
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

	@Column(name = "acctterminatecause")
	public String getAcctTerminateCause() {
		return acctTerminateCause;
	}

	public void setAcctTerminateCause(String acctTerminateCause) {
		this.acctTerminateCause = acctTerminateCause;
	}

	@Column(name = "servicetype")
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@Column(name = "xascendsessionsvrkey")
	public String getxAscendSessionSvrKey() {
		return xAscendSessionSvrKey;
	}

	public void setxAscendSessionSvrKey(String xAscendSessionSvrKey) {
		this.xAscendSessionSvrKey = xAscendSessionSvrKey;
	}

	@Column(name = "framedprotocol")
	public String getFramedProtocol() {
		return framedProtocol;
	}

	public void setFramedProtocol(String framedProtocol) {
		this.framedProtocol = framedProtocol;
	}

	@Column(name = "framedipaddress")
	@Type(type="inet")
	public InetAddress getFramedIPAddress() {
		return framedIPAddress;
	}

	public void setFramedIPAddress(InetAddress framedIPAddress) {
		this.framedIPAddress = framedIPAddress;
	}

	@Column(name = "acctstartdelay")
	public Integer getAcctStartDelay() {
		return acctStartDelay;
	}

	public void setAcctStartDelay(Integer acctStartDelay) {
		this.acctStartDelay = acctStartDelay;
	}

	@Column(name = "acctstopdelay")
	public Integer getAcctStopDelay() {
		return acctStopDelay;
	}

	public void setAcctStopDelay(Integer acctStopDelay) {
		this.acctStopDelay = acctStopDelay;
	}

}
