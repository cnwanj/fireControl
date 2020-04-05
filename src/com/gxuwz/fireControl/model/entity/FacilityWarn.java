package com.gxuwz.fireControl.model.entity;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

/*
 * 设备报警信息
 */
@Component
public class FacilityWarn {
	// 自增主键
	private Long warnId; 		
	// 设备报警时间
	private Timestamp warnTime; 
	// 设备编号（外键）
	private Facility fac; 		

	public Long getWarnId() {
		return warnId;
	}

	public void setWarnId(Long warnId) {
		this.warnId = warnId;
	}

	public Timestamp getWarnTime() {
		return warnTime;
	}

	public void setWarnTime(Timestamp warnTime) {
		this.warnTime = warnTime;
	}

	public Facility getFac() {
		return fac;
	}

	public void setFac(Facility fac) {
		this.fac = fac;
	}

	@Override
	public String toString() {
		return "FacilityWarn [warnId=" + warnId + ", warnTime=" + warnTime
				+ ", fac=" + fac + "]";
	}

}
