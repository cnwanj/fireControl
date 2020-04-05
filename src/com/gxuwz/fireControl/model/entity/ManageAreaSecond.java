package com.gxuwz.fireControl.model.entity;

import org.springframework.stereotype.Component;

/*
 * 二级管辖域
 */
@Component
public class ManageAreaSecond {
	//  二级管辖域编号
	private String asId; 
	//  名称
	private String asName; 
	//  二级管辖员编号
	private String usId; 
	//	一级管辖域（外键：一个二级管辖域只属于一个一级管辖域 ）
	private ManageAreaFirst af;

	public String getAsId() {
		return asId;
	}

	public void setAsId(String asId) {
		this.asId = asId;
	}

	public String getAsName() {
		return asName;
	}

	public void setAsName(String asName) {
		this.asName = asName;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public ManageAreaFirst getAf() {
		return af;
	}

	public void setAf(ManageAreaFirst af) {
		this.af = af;
	}

	@Override
	public String toString() {
		return "ManageAreaSecond [asId=" + asId + ", asName=" + asName
				+ ", usId=" + usId + ", af=" + af + "]";
	}
}