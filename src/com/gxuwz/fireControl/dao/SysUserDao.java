package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.SysUsers;

public interface SysUserDao extends BaseDao<SysUsers> {

	Result<SysUsers> findByPage(SysUsers user, Integer page, Integer row);

	SysUsers getOneByUserName(String loginName);

	SysUsers login(String loginName, String loginPwd);

//	SysUsers getOneByUserCode(String userId);
	
	/**
	 * 根据工号查询用户登录信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月5日
	 * @param userCode
	 * @return
	 */
	SysUsers getOneByUserCode(String userCode);
	//查询一种身份
	public List<SysUsers> findOneUserType(String userType);
	
}
