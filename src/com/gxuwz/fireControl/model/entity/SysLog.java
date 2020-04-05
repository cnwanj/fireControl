package com.gxuwz.fireControl.model.entity;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

/*
 * 日志
 */
@Component
public class SysLog {
	// 自增主键
	private Long logId; 
	// 日志编号
	private String logCode;
	// 操作内容
	private String logContent; 
	// 日志类型（删除人员和设备、解绑设备等）
	private Integer logType; 
	// 操作时间
	private Timestamp logTime; 
	// 被操作的设备或人员编号
	private String entityId;
	// 操作用户工号（外键）
	private SysUsers user; 

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getLogCode() {
		return logCode;
	}

	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}

	public SysUsers getUser() {
		return user;
	}

	public void setUser(SysUsers user) {
		this.user = user;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Timestamp getLogTime() {
		return logTime;
	}

	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	@Override
	public String toString() {
		return "SysLog [logId=" + logId + ", logCode=" + logCode
				+ ", logContent=" + logContent + ", logType=" + logType
				+ ", logTime=" + logTime + ", entityId=" + entityId + ", user="
				+ user + "]";
	}

}
