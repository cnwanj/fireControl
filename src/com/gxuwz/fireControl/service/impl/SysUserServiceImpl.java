package com.gxuwz.fireControl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.SysUserDao;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.gxuwz.fireControl.service.SysUserService;

/**
 * 用户登录信息
 * @author 韦永恒
 * @date 2020.01.24
 */
@Transactional
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	private Class<SysUsers> su = SysUsers.class;

	// 登录
	public SysUsers login(String loginName, String loginPwd) {
		return sysUserDao.login(loginName, loginPwd);
	}

	// 添加
	public boolean save(SysUsers user) {
		if (sysUserDao.getOneByUserName(user.getLoginName()) == null) {
			sysUserDao.save(user);
			return true;
		} else {
			return false;
		}
	}

	// 删除
	public boolean delete(SysUsers user) {
		if (sysUserDao.get(su, user.getUserId()) != null) {
			sysUserDao.remove(su, user.getUserId());
			return true;
		} else {
			return false;
		}
	}

	// 修改
	public boolean update(SysUsers user) {
		sysUserDao.update(user);
		return true;
	}

	// 获取详情
	public SysUsers getOne(Long userId) {
		return sysUserDao.get(su, userId);
	}

	// 分页查询用户信息
	public Result<SysUsers> findByPage(SysUsers user, Integer page, Integer limit) {
		return sysUserDao.findByPage(user, page, limit);
	}

	// 根据工号查询登录表信息
	public SysUsers getOneByUserCode(String userId) {
		return sysUserDao.getOneByUserCode(userId);
	}
	
	@Override
	public List<SysUsers> findOneUserType(String userType) {
		return sysUserDao.findOneUserType(userType);
	}

}
