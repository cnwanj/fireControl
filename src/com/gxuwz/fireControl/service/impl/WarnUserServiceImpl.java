package com.gxuwz.fireControl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.SysUserDao;
import com.gxuwz.fireControl.dao.WarnUserDao;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.gxuwz.fireControl.model.entity.WarnUser;
import com.gxuwz.fireControl.service.WarnUserService;

/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
@Transactional
@Service("warnUserService")
public class WarnUserServiceImpl  implements WarnUserService{
	
	@Autowired
	private WarnUserDao warnUserdao;
	private Class<WarnUser> wu = WarnUser.class;
	@Autowired
	private SysUserDao sysUserDao;

	//1在用户登录表中查询用户名是否存在 2也要在用户登录表存入新加的报警联系人信息
	@Override
	public boolean save(WarnUser warnUser) {
		if(sysUserDao.getOneByUserName(warnUser.getLoginName()) == null) {
			warnUserdao.save(warnUser);
			SysUsers sysUsers =new SysUsers();
			sysUsers.setLoginName(warnUser.getLoginName());
			sysUsers.setLoginPwd(warnUser.getLoginPwd());			
			sysUsers.setUserCode(warnUser.getUserId());
			sysUsers.setUserName(warnUser.getUserName());
			sysUsers.setUserPhone(warnUser.getUserPhone());
			sysUsers.setUserType(6);
			sysUserDao.save(sysUsers);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(WarnUser warnUser) {
		if (warnUserdao.get(wu, warnUser.getUserId()) != null) {
			warnUserdao.remove(wu, warnUser.getUserId());
			// 根据工号查询用户登录信息
			SysUsers sysUsers = sysUserDao.getOneByUserCode(warnUser.getUserId());
			if (sysUsers != null) {
				// 删除用户登录信息
				sysUserDao.remove(sysUsers);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(WarnUser warnUser) {
		warnUserdao.update(warnUser);
		// 根据工号查询用户登录信息
		SysUsers sysUsers = sysUserDao.getOneByUserCode(warnUser.getUserId());
		//获取更新信息
		sysUsers.setLoginName(warnUser.getLoginName());
		sysUsers.setLoginPwd(warnUser.getLoginPwd());			
		sysUsers.setUserName(warnUser.getUserName());
		sysUsers.setUserPhone(warnUser.getUserPhone());
		sysUserDao.update(sysUsers);
		return true;
	}

	@Override
	public WarnUser getOne(String userId) {
		return warnUserdao.get(wu, userId);
	}

	@Override
	public Result<WarnUser> findByPage(WarnUser warnUser, Integer page, Integer limit) {
		return warnUserdao.findByPage(warnUser,page, limit);
	}

	@Override
	public List<WarnUser> findAll(String asId) {
		return warnUserdao.findAll(asId);
	}

}
