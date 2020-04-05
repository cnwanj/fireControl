package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;

public interface DeptUserDao extends BaseDao<DeptUser> {

	Result<DeptUser> findByPage(DeptUser deptUser, int page, int row);

	DeptUser getOneByUserName(String loginName);
	
	/**
	 * 根据单位编号查询对应的一级管辖域
	 * 
	 * @Description TODO
	 * @author ；刘家乐
	 * @date 2020年2月7日
	 * @param deptId 单位编号
	 * @return
	 */
	DeptUser getAfByDeptId(String deptId);
    //后添
	List<ManageUserFirst> getManageUserFirstByDeptUserId(String userId);
}
