package com.gxuwz.fireControl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.SysLogDao;
import com.gxuwz.fireControl.dao.SysUserDao;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;
import com.gxuwz.fireControl.model.entity.SysLog;
import com.gxuwz.fireControl.service.SysLogService;
/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 
 */
@Transactional
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	@SuppressWarnings("unchecked")
	@Override
	public Result<SysLog> findByPage(SysLog sysLog, int page, int row) {
		// TODO 自动生成的方法存根
		return sysLogDao.findByPage(sysLog, page, row);
	}

	//@Override
	//public boolean save(SysLog sysLog) {
		// TODO 自动生成的方法存根
		//return false;
	//}

	@Override
	public boolean delete(SysLog sysLog) {
		if (sysLogDao.get(SysLog.class, sysLog.getLogId()) != null) {
			sysLogDao.remove(SysLog.class, sysLog.getLogId());
			return true;
		}
		return false;
	}

}
