package com.gxuwz.fireControl.model.entity;

import org.springframework.stereotype.Component;

/*
 * 用户登录
 */
@Component
public class SysUsers {
	// 自增主键
	private Long userId; 
	// 登录用户名
	private String loginName; 
	// 密码
	private String loginPwd; 
	// 工号
	private String userCode; 
	// 姓名
	private String userName; 
	// 电话
	private Long userPhone; 
	// 用户类型（1: 超级管理员、2: 设备安装人员、3: 单位管理员、4：一级管辖员、:5: 二级管辖员、6:报警联系人）
	private Integer userType; 

	public SysUsers() {
	}
	
	public SysUsers(Long userId, String loginName, String loginPwd,
			String userCode, String userName, Long userPhone, Integer userType) {
		this.userId = userId;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.userCode = userCode;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(Long userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "SysUser [userId=" + userId + ", loginName=" + loginName
				+ ", loginPwd=" + loginPwd + ", userCode=" + userCode
				+ ", userName=" + userName + ", userPhone=" + userPhone
				+ ", userType=" + userType + "]";
	}

}
