package com.gxuwz.fireControl.model.entity;

import org.springframework.stereotype.Component;

/*
 * 一级管辖域
 */
@Component
public class ManageAreaFirst {
	// 一级管辖域编号
	private String afId; 	
	// 名称
	private String afName; 
	// 一级管辖员编号
	private String ufId; 
	// 单位编号（外键：一个一级管辖域只属于一个单位）
	private Dept dept; 	

	public String getAfId() {
		return afId;
	}

	public void setAfId(String afId) {
		this.afId = afId;
	}

	public String getAfName() {
		return afName;
	}

	public void setAfName(String afName) {
		this.afName = afName;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getUfId() {
		return ufId;
	}

	public void setUfId(String ufId) {
		this.ufId = ufId;
	}

	@Override
	public String toString() {
		return "ManageAreaFirst [afId=" + afId + ", afName=" + afName
				+ ", dept=" + dept + ", ufId=" + ufId + "]";
	}

}
