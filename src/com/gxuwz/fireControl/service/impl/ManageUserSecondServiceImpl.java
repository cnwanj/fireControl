package com.gxuwz.fireControl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.ManageAreaSecondDao;
import com.gxuwz.fireControl.dao.ManageUserSecondDao;
import com.gxuwz.fireControl.dao.SysUserDao;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.gxuwz.fireControl.service.ManageUserSecondService;

/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月4日
 */
@Transactional
@Service("manageUserSecondService")
public class ManageUserSecondServiceImpl implements ManageUserSecondService {

	@Autowired
	private ManageUserSecondDao manageUserSecondDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private ManageAreaSecondDao manageAreaSecondDao;

	@Override
	public Result<ManageUserSecond> findByPage(ManageUserSecond userSecond, Integer page, Integer row) {

		return manageUserSecondDao.findByPage(userSecond, page, row);
	}

	/**
	 * 判断：根据二级管辖员用户名查询二级管辖员信息如果为空则保存和返回true，否则不保存和返回false。
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @param userSecond
	 * @return
	 */
	@Override
	public boolean save(ManageUserSecond userSecond) {
		if (manageUserSecondDao.getManageUserSecondByLoginName(userSecond.getLoginName()) == null) {
			manageUserSecondDao.save(userSecond);
			if (userSecond.getAs().getAsId() != null) {
				ManageAreaSecond manageAreaSecond = manageAreaSecondDao.get(ManageAreaSecond.class, userSecond.getAs().getAsId());
				manageAreaSecond.setUsId(userSecond.getUsId());
				manageAreaSecondDao.update(manageAreaSecond);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断：根据二级管辖员工号查询二级管辖员信息如果不为空则删除和返回true，否则不删除和返回false。
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @param userSecond
	 * @return
	 */
	@Override
	public boolean delete(ManageUserSecond userSecond) {
		// 根据二级管辖员工号查询二级管辖员信息
		if (manageUserSecondDao.get(ManageUserSecond.class, userSecond.getUsId()) != null) {
			// 删除二级管辖员信息
			manageUserSecondDao.remove(ManageUserSecond.class, userSecond.getUsId());
			// 根据工号查询用户登录信息
			SysUsers sysUsers = sysUserDao.getOneByUserCode(userSecond.getUsId());
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
	 * 判断：根据二级管辖员工号查询二级管辖员信息如果不为空则更新和返回true，否则不更新和返回false。
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月4日
	 * @param userSecond
	 * @return
	 */
	@Override
	public boolean update(ManageUserSecond userSecond) {
		// 更新二级管辖员信息
		manageUserSecondDao.update(userSecond);
		// 1、根据二级管辖员工号获取用户登录信息
		SysUsers sysUsers = sysUserDao.getOneByUserCode(userSecond.getUsId());
		// 2、设置各个属性
		sysUsers.setLoginName(userSecond.getLoginName());
		sysUsers.setLoginPwd(userSecond.getLoginPwd());
		sysUsers.setUserName(userSecond.getUsName());
		sysUsers.setUserPhone(userSecond.getUsPhone());
		// 3、更新用户登录信息到sys_users表
		sysUserDao.update(sysUsers);
		// 4、
		ManageAreaSecond manageAreaSecond = manageAreaSecondDao.get(ManageAreaSecond.class, userSecond.getAs().getAsId());
		manageAreaSecond.setUsId(userSecond.getUsId());
		manageAreaSecondDao.update(manageAreaSecond);
		return true;
	}

	@Override
	public ManageUserSecond getOne(String usId) {

		return manageUserSecondDao.get(ManageUserSecond.class, usId);
	}

	/**
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @param ufId
	 * @return
	 */
	@Override
	public List<ManageUserSecond> getManageUserSecondByUfId(String ufId) {

		return manageUserSecondDao.getManageUserSecondByUfId(ufId);
	}

}
