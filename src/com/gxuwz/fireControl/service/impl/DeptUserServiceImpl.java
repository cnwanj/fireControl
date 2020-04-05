package com.gxuwz.fireControl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.DeptUserDao;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.service.DeptUserService;

/**
 * 单位管理员信息
 * @author 韦永恒
 * @date 2020.02.09
 */
@Transactional
@Service("deptUserService")
public class DeptUserServiceImpl implements DeptUserService {
	@Autowired
	private DeptUserDao deptUserDao;
	private Class<DeptUser> du = DeptUser.class;
	
	// 分页显示
	public Result<DeptUser> findByPage(DeptUser deptUser, int page, int row) {
		return deptUserDao.findByPage(deptUser, page, row);
	}

	// 添加单位管理员信息
	public boolean save(DeptUser deptUser) {
		if (deptUserDao.getOneByUserName(deptUser.getLoginName()) == null) {
			deptUserDao.save(deptUser);
			return true;
		} else {
			return false;
		}
	}

	// 删除
	public boolean delete(DeptUser deptUser) {
		if (deptUserDao.get(du, deptUser.getUserId()) != null) {
			deptUserDao.remove(du, deptUser.getUserId());
			return true;
		} else {
			return false;
		}
	}

	// 显示详情信息
	public DeptUser getOne(String userId) {
		return deptUserDao.get(du, userId);
	}

	// 修改信息
	public boolean update(DeptUser deptUser) {
		deptUserDao.update(deptUser);
		return true;
	}
	
	@Override
	public DeptUser login(String loginName, String loginPwd) {
		// TODO 自动生成的方法存根
		return null;
	}
	
}
