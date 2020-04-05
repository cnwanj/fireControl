package com.gxuwz.fireControl.model.entity;

import org.springframework.stereotype.Component;

/*
 * 一级管辖员
 */
@Component
public class ManageUserFirst {
	// 一级管辖员工号
	private String ufId; 
	// 姓名
	private String ufName; 
	// 用户名
	private String loginName;
	// 密码
	private String loginPwd;
	// 电话
	private Long ufPhone; 
	// 人员类型: （0：管辖责任人、1：管辖员）
	private Integer ufType; 
	// 一级管辖域编号（外键）
	private ManageAreaFirst af; 
	// 单位管理员
	private DeptUser deptUser;

	public String getUfId() {
		return ufId;
	}

	public void setUfId(String ufId) {
		this.ufId = ufId;
	}

	public String getUfName() {
		return ufName;
	}

	public void setUfName(String ufName) {
		this.ufName = ufName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Long getUfPhone() {
		return ufPhone;
	}

	public void setUfPhone(Long ufPhone) {
		this.ufPhone = ufPhone;
	}

	public Integer getUfType() {
		return ufType;
	}

	public void setUfType(Integer ufType) {
		this.ufType = ufType;
	}

	public ManageAreaFirst getAf() {
		return af;
	}

	public void setAf(ManageAreaFirst af) {
		this.af = af;
	}

	public DeptUser getDeptUser() {
		return deptUser;
	}

	public void setDeptUser(DeptUser deptUser) {
		this.deptUser = deptUser;
	}

	@Override
	public String toString() {
		return "ManageUserFirst [ufId=" + ufId + ", ufName=" + ufName
				+ ", loginName=" + loginName + ", loginPwd=" + loginPwd
				+ ", ufPhone=" + ufPhone + ", ufType=" + ufType + ", af=" + af
				+ ", deptUser=" + deptUser + "]";
	}

}
