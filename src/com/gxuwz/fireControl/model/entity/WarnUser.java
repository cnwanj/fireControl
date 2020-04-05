package com.gxuwz.fireControl.model.entity;

import org.springframework.stereotype.Component;

/*
 * 报警联系人
 */
@Component
public class WarnUser {
	// 报警联系人工号
	private String userId; 
	// 姓名
	private String userName; 
	// 用户名
	private String loginName;
	// 密码
	private String loginPwd;
	// 电话
	private Long userPhone; 
	// 安装点编号（外键）
	private InstallPoint point; 

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

	public InstallPoint getPoint() {
		return point;
	}

	public void setPoint(InstallPoint point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "WarnUser [userId=" + userId + ", userName=" + userName
				+ ", loginName=" + loginName + ", loginPwd=" + loginPwd
				+ ", userPhone=" + userPhone + ", point=" + point + "]";
	}
}
