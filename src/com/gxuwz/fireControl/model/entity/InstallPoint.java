package com.gxuwz.fireControl.model.entity;

import org.springframework.stereotype.Component;

/*
 * 安装点
 */
@Component
public class InstallPoint {
	// 自增主键
	private Long insId;
	// 安装点编号
	private String insCode;
	// 名称
	private String insName;
	// 安装地址
	private String insAddress;
	// 二级管辖域编号（外键）
	private ManageAreaSecond as;

	public Long getInsId() {
		return insId;
	}

	public void setInsId(Long insId) {
		this.insId = insId;
	}

	public String getInsCode() {
		return insCode;
	}

	public void setInsCode(String insCode) {
		this.insCode = insCode;
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}

	public String getInsAddress() {
		return insAddress;
	}

	public void setInsAddress(String insAddress) {
		this.insAddress = insAddress;
	}

	public ManageAreaSecond getAs() {
		return as;
	}

	public void setAs(ManageAreaSecond as) {
		this.as = as;
	}

	@Override
	public String toString() {
		return "InstallPoint [insId=" + insId + ", insCode=" + insCode
				+ ", insName=" + insName + ", insAddress=" + insAddress
				+ ", as=" + as + "]";
	}
}
