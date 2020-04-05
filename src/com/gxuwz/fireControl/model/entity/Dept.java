package com.gxuwz.fireControl.model.entity;

import org.springframework.stereotype.Component;

/*
 * 单位（公司）
 */
@Component
public class Dept {
	// 单位编号
	private String deptId; 
	// 单位名称
	private String deptName; 
	// 单位地址
	private String deptAddress;
	// 单位管理员工号
	private String userId;

	public Dept() {
	}
	
	public Dept(String deptId, String deptName, String deptAddress,
			String userId) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptAddress = deptAddress;
		this.userId = userId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptAddress() {
		return deptAddress;
	}

	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptName=" + deptName
				+ ", deptAddress=" + deptAddress + ", userId=" + userId + "]";
	}
}
