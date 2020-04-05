package com.gxuwz.fireControl.service;

import java.util.List;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.SysUsers;


public interface SysUserService {

	boolean save(SysUsers user);

	boolean delete(SysUsers user);

	boolean update(SysUsers user);

	SysUsers getOne(Long userId);
	
	Result<SysUsers> findByPage(SysUsers user, Integer page, Integer limit);

	SysUsers login(String loginName, String loginPwd);
	
	public List<SysUsers> findOneUserType(String userType);

	SysUsers getOneByUserCode(String userId);

}
