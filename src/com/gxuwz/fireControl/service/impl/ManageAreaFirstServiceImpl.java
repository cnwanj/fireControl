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
import com.gxuwz.fireControl.dao.ManageUserFirstDao;
import com.gxuwz.fireControl.dao.ManageUserSecondDao;
import com.gxuwz.fireControl.dao.SysUserDao;
import com.gxuwz.fireControl.model.entity.Dept;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.gxuwz.fireControl.service.ManageAreaFirstService;

/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 2020年2月6日
 */
@Transactional
@Service("manageAreaFirstService")
public class ManageAreaFirstServiceImpl implements ManageAreaFirstService {

	@Autowired
	private ManageAreaFirstDao manageAreaFirstDao;
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private DeptUserDao deptUserDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private ManageUserFirstDao manageUserFirstDao;

	@Override
	public List<ManageAreaFirst> getManageAreaFirstByDeptUserId(String userId) {
		List<ManageAreaFirst> manageAreaFirstByDeptId = new ArrayList<ManageAreaFirst>();
		//首先根据单位管理员工号查询对应的单位管理员信息
		DeptUser deptUser = deptUserDao.get(DeptUser.class, userId);
		//然后根据查到到单位编号DeptId查询对应的单位信息
		Dept dept = deptDao.getDeptByDeptUserId(deptUser.getDept().getDeptId());
		
		if(dept == null) {
			return manageAreaFirstByDeptId;
		}
		String deptId = dept.getDeptId();
		manageAreaFirstByDeptId = manageAreaFirstDao.getManageAreaFirstByDeptId(deptId);
		return manageAreaFirstByDeptId;
	}
	//后添
	@Override
	public Result<ManageAreaFirst> findByPage(ManageAreaFirst manageAreaFirst, int page, int row) {
		// TODO 自动生成的方法存根
		return manageAreaFirstDao.findByPage(manageAreaFirst, page, row);
	}
	/**
	 * 判断：根据一级管辖域编号查询一级管辖域信息如果为空则保存和返回true，否则不保存和返回false。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @param manageAreaFirst
	 * @return
	 */
	@Override
	public boolean save(ManageAreaFirst manageAreaFirst) {
		if (manageAreaFirstDao.getManageAreaFirstByAfId(manageAreaFirst.getAfId()) == null) {
			manageAreaFirstDao.save(manageAreaFirst);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断：根据一级管域编号查询一级管辖域信息如果不为空则更新和返回true，否则不更新和返回false。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @param manageAreaFirst
	 * @return
	 */
	@Override
	public boolean update(ManageAreaFirst manageAreaFirst) {
		// 更新一级管辖域信息
		manageAreaFirstDao.update(manageAreaFirst);
		ManageUserFirst manageUserFirst = manageUserFirstDao.get(ManageUserFirst.class, manageAreaFirst.getUfId());
		manageUserFirst.setAf(manageAreaFirst);
		manageUserFirstDao.update(manageUserFirst);
		return true;
	}
	/**
	 * 判断：根据一级管辖域编号查询一级管辖域信息如果不为空则删除和返回true，否则不删除和返回false。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @param manageAreaFirst
	 * @return
	 */
	@Override
	public boolean delete(ManageAreaFirst manageAreaFirst) {
		// 根据一级管辖域编号查询一级管辖域信息
		if (manageAreaFirstDao.get(ManageAreaFirst.class, manageAreaFirst.getAfId()) != null) {
			// 删除一级管辖域信息
			manageAreaFirstDao.remove(ManageAreaFirst.class, manageAreaFirst.getAfId());
			return true;
		}
		return false;
	}
	@Override
	public ManageAreaFirst getOne(String afId) {
		return manageAreaFirstDao.get(ManageAreaFirst.class, afId);
	}
}
   
	
