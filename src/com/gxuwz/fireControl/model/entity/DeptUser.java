package com.gxuwz.fireControl.model.entity;

import org.springframework.stereotype.Component;

/*
 * 单位管理员
 */
@Component
public class DeptUser {
	// 工号
	private String userId; 
	// 姓名
	private String userName;
	// 用户名
	private String loginName;
	// 密码
	private String loginPwd;
	// 电话
	private Long userPhone; 
	// 单位编号（外键一对一）
	private Dept dept;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(Long userPhone) {
		this.userPhone = userPhone;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "DeptUser [userId=" + userId + ", userName=" + userName
				+ ", loginName=" + loginName + ", loginPwd=" + loginPwd
				+ ", userPhone=" + userPhone + ", dept=" + dept + "]";
	}
}
