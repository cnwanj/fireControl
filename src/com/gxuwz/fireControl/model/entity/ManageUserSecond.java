package com.gxuwz.fireControl.model.entity;

import org.springframework.stereotype.Component;

/*
 * 二级管辖员
 */
@Component
public class ManageUserSecond {
	// 二级管辖员工号
	private String usId; 
	// 姓名
	private String usName; 
	// 用户名
	private String loginName;
	// 密码
	private String loginPwd;
	// 电话
	private Long usPhone; 
	// 人员类型: （0：管辖责任人、1：管辖员）
	private Integer usType; 
	// 一级管辖员编号（外键）
	private ManageUserFirst uf; 
	// 二级管辖域编号（外键）
	private ManageAreaSecond as;

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public String getUsName() {
		return usName;
	}

	public void setUsName(String usName) {
		this.usName = usName;
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

	public Long getUsPhone() {
		return usPhone;
	}

	public void setUsPhone(Long usPhone) {
		this.usPhone = usPhone;
	}

	public Integer getUsType() {
		return usType;
	}

	public void setUsType(Integer usType) {
		this.usType = usType;
	}

	public ManageUserFirst getUf() {
		return uf;
	}

	public void setUf(ManageUserFirst uf) {
		this.uf = uf;
	}

	public ManageAreaSecond getAs() {
		return as;
	}

	public void setAs(ManageAreaSecond as) {
		this.as = as;
	}

	@Override
	public String toString() {
		return "ManageUserSecond [usId=" + usId + ", usName=" + usName
				+ ", loginName=" + loginName + ", loginPwd=" + loginPwd
				+ ", usPhone=" + usPhone + ", usType=" + usType + ", uf=" + uf
				+ ", as=" + as + "]";
	}
}
