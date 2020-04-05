package com.gxuwz.fireControl.model.entity;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

/*
 * 设备
 */
@Component
public class Facility {
	private String facId; // 设备编号
	private String facName; // 设备名称
	private Timestamp facTime; // 安装时间
	private String facAddress; // 安装地址
	private Integer facStatus; // 设备状态（1：正常、0：报警）
	private Integer facBind; // 绑定状态（1：已绑定、0：已解绑）
	 private SysUsers sysUsers; //安装人员工号（外键）
	 private WarnUser warnUser; //报警联系人工号（外键）
	 private InstallPoint point; //安装点编号（外键）

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getFacName() {
		return facName;
	}

	public void setFacName(String facName) {
		this.facName = facName;
	}

	public Timestamp getFacTime() {
		return facTime;
	}

	public void setFacTime(Timestamp facTime) {
		this.facTime = facTime;
	}

	public String getFacAddress() {
		return facAddress;
	}

	public void setFacAddress(String facAddress) {
		this.facAddress = facAddress;
	}

	public Integer getFacStatus() {
		return facStatus;
	}

	public void setFacStatus(Integer facStatus) {
		this.facStatus = facStatus;
	}

	public Integer getFacBind() {
		return facBind;
	}

	public void setFacBind(Integer facBind) {
		this.facBind = facBind;
	}

	
	 public SysUsers getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	public WarnUser getWarnUser() {
		return warnUser;
	}
	public void setWarnUser(WarnUser warnUser) {
		this.warnUser = warnUser;
	}
	public InstallPoint getPoint() {
		return point;
	}
	public void setPoint(InstallPoint point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "facility [facId=" + facId + ", facName=" + facName
				+ ", facTime=" + facTime + ", facAddress=" + facAddress
				+ ", facStatus=" + facStatus + ", facBind=" + facBind
				 + ", sysUsers=" + sysUsers + ", warnUser=" + warnUser
				 + ", point=" + point
				+ "]";
	}
}
