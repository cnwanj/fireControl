package com.gxuwz.fireControl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.DeptDao;
import com.gxuwz.fireControl.dao.DeptUserDao;
import com.gxuwz.fireControl.dao.ManageAreaFirstDao;
import com.gxuwz.fireControl.dao.ManageAreaSecondDao;
import com.gxuwz.fireControl.dao.ManageUserFirstDao;
import com.gxuwz.fireControl.dao.ManageUserSecondDao;
import com.gxuwz.fireControl.dao.SysUserDao;
import com.gxuwz.fireControl.model.entity.Dept;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.gxuwz.fireControl.service.ManageUserFirstService;
import com.gxuwz.fireControl.service.ManageUserSecondService;

/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 2020年2月7日
 */
@Transactional
@Service("manageUserFirstService")
public class ManageUserFirstServiceImpl implements ManageUserFirstService {

	@Autowired
	private ManageUserFirstDao manageUserFirstDao;

	@Autowired
	private SysUserDao sysUserDao;
	//后添
	@Autowired
	private DeptUserDao deptUserDao;
	@Autowired
	private ManageAreaFirstDao manageAreaFirstDao;
	@Override
	public Result<ManageUserFirst> findByPage(ManageUserFirst userFirst, Integer page, Integer row) {

		return manageUserFirstDao.findByPage(userFirst, page, row);
	}

	/**
	 * 判断：根据一级管辖员用户名查询一级管辖员信息如果为空则保存和返回true，否则不保存和返回false。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param userFirst
	 * @return
	 */
	@Override
	public boolean save(ManageUserFirst userFirst) {
		if (manageUserFirstDao.getManageUserFirstByLoginName(userFirst.getLoginName()) == null) {
			manageUserFirstDao.save(userFirst);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断：根据一级管辖员工号查询一级管辖员信息如果不为空则删除和返回true，否则不删除和返回false。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param userFirst
	 * @return
	 */
	@Override
	public boolean delete(ManageUserFirst userFirst) {
		// 根据一级管辖员工号查询一级管辖员信息
		if (manageUserFirstDao.get(ManageUserFirst.class, userFirst.getUfId()) != null) {
			// 删除一级管辖员信息
			manageUserFirstDao.remove(ManageUserFirst.class, userFirst.getUfId());
			// 根据工号查询用户登录信息
			SysUsers sysUsers = sysUserDao.getOneByUserCode(userFirst.getUfId());
			// 如果用户登录信息存在，如不存在则无需删除
			if (sysUsers != null) {
				// 删除用户登录信息
				sysUserDao.remove(sysUsers);
			}
			return true;
		}
		return false;
	}

	/**
	 * 判断：根据一级管辖员工号查询一级管辖员信息如果不为空则更新和返回true，否则不更新和返回false。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param userFirst
	 * @return
	 */
	@Override
	public boolean update(ManageUserFirst userFirst) {
		// 更新一级管辖员信息
		manageUserFirstDao.update(userFirst);
		// 1、根据一级管辖员工号获取用户登录信息
		SysUsers sysUsers = sysUserDao.getOneByUserCode(userFirst.getUfId());
		// 2、设置各个属性
		sysUsers.setLoginName(userFirst.getLoginName());
		sysUsers.setLoginPwd(userFirst.getLoginPwd());
		sysUsers.setUserName(userFirst.getUfName());
		sysUsers.setUserPhone(userFirst.getUfPhone());
		// 3、更新用户登录信息到sys_users表
		sysUserDao.update(sysUsers);
		
		return true;
	}

	@Override
	public ManageUserFirst getOne(String ufId) {

		return manageUserFirstDao.get(ManageUserFirst.class, ufId);
	}
	
	
	//后添

	@Override
	public List<ManageUserFirst> getManageUserFirstByDeptUserId(String userId) {
		List<ManageUserFirst> manageUserFirstByDeptUserId = new ArrayList<ManageUserFirst>();
		//首先根据单位管理员工号查询对应的单位管理员信息
		manageUserFirstByDeptUserId = deptUserDao.getManageUserFirstByDeptUserId(userId);
		return manageUserFirstByDeptUserId;
	}
	
	

}
