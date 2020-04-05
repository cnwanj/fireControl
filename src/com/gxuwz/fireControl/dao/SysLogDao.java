package com.gxuwz.fireControl.dao;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.SysLog;
import com.gxuwz.fireControl.model.entity.SysUsers;
/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 
 */
public interface SysLogDao extends BaseDao<SysLog> {

	Result<SysLog> findByPage(SysLog sysLog, int page, int row);

}
