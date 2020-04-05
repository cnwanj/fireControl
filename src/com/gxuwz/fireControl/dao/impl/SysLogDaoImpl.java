package com.gxuwz.fireControl.dao.impl;

import org.springframework.stereotype.Repository;
import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.SysLogDao;
import com.gxuwz.fireControl.model.entity.SysLog;
/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 
 */
@SuppressWarnings("unchecked")
@Repository("sysLogDao")
public class SysLogDaoImpl extends BaseDaoImpl<SysLog> implements SysLogDao {

	@Override
	public Result<SysLog> findByPage(SysLog sysLog, int page, int row) {
		String queryString = "from SysLog where 1=1";
		if (null != sysLog.getUser() && null != sysLog.getUser().getUserId()) {
			queryString = queryString + " and user like '%" + sysLog.getUser().getUserId() + "%'";
		}
		int start = (page - 1) * row;
		int limit = row;
		return (Result<SysLog>) super.find(queryString, null, null, start, limit);
	}

}
