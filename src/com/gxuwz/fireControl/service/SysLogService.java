package com.gxuwz.fireControl.service;

import com.gxuwz.core.pagination.Result;

import com.gxuwz.fireControl.model.entity.SysLog;
/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 
 */

public interface SysLogService {

	Result<SysLog> findByPage(SysLog sysLog, int page, int row);

	//boolean save(SysLog sysLog);

	boolean delete(SysLog sysLog);

}
