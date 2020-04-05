package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.WarnUser;
/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
public interface WarnUserDao extends BaseDao<WarnUser>{
	
	Result<WarnUser> findByPage(WarnUser warnUser,Integer page, Integer row);
		
	WarnUser getOneByLoginName(String loginName);
	
	public List<WarnUser> findAll(String asId);
}
